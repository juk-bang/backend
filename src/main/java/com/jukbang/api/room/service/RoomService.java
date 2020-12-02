package com.jukbang.api.room.service;

import com.jukbang.api.room.dto.LandlordDto;
import com.jukbang.api.room.dto.RoomsDto;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.entity.UnivFilter;
import com.jukbang.api.room.exception.NotYourRoomException;
import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.room.repository.UnivFilterRepository;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.response.GetRoomDetailResponse;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 방 서비스
 *
 * @author always0ne
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final UserRepository userRepository;
  private final UnivFilterRepository univFilterRepository;

  /**
   * 방 리스트를 필터링 하여 조회
   * 쿼리문을 직접 작성하여 univId 를 제외하고 Nullable하다.
   *
   * @param univId        대학교 ID
   * @param layout        방 구조
   * @param floor         층
   * @param scale         방 넓이
   * @param monthlyLease  월세
   * @param adminExpenses 관리비
   * @param deposit       보증금
   * @param grade         평점
   * @param distance      학교와의 거리
   * @param pageable      페이징 정보
   * @return 방 리스트
   */
  @Transactional
  public Page<RoomsDto> getRooms(long univId, Integer layout, Double floor, String scale,
                                 String monthlyLease, String adminExpenses, String deposit,
                                 String grade, String distance, String requestUserId,
                                 Pageable pageable) {


    Double[] scales = parseRange(scale);
    Double[] monthlyLeases = parseRange(monthlyLease);
    Double[] adminExpense = parseRange(adminExpenses);
    Double[] deposits = parseRange(deposit);
    Double[] grades = parseRange(grade);
    Double[] distances = parseRange(distance);
    if (!requestUserId.equals("anonymousUser")) {
      userRepository.findByUserId(requestUserId).orElseThrow(UserNotFoundException::new).getFilter()
          .updateFilter(layout, floor, scales, monthlyLeases, adminExpense, deposits, grades,
              distances);
    }
    UnivFilter filter = univFilterRepository.findById(univId).orElse(new UnivFilter());
    filter.updateFilter(scales, monthlyLeases, adminExpense, deposits, grades, distances);
    univFilterRepository.save(filter);
    return this.roomRepository
        .findAllByUnivIdWithFilter(univId, layout, floor, scales[0], scales[1], monthlyLeases[0],
            monthlyLeases[1], adminExpense[0], adminExpense[1], deposits[0], deposits[1], grades[0],
            grades[1], distances[0], distances[1], pageable);
  }

  /**
   * 집주인이 올린 매물들 리스트 조회
   *
   * @param sellerId 집주인 ID
   * @param pageable 페이징 정보
   * @return 집주인이 올린 매물들
   */
  @Transactional
  public Page<LandlordDto> getSellerRooms(String sellerId, Pageable pageable) {
    return roomRepository.findAllBySellerId(sellerId, pageable);
  }

  /**
   * 방 상세조회
   *
   * @param roomId 방 ID
   * @return 집주인이 올린 매물들
   */
  @Transactional
  public GetRoomDetailResponse getRoomDetail(long roomId) {
    return new GetRoomDetailResponse(
        roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new));
  }

  /**
   * 방 등록하기
   *
   * @param sellerId          집주인 ID
   * @param createRoomRequest
   * @return 집주인이 올린 매물들
   */
  @Transactional
  public Long registerRoom(String sellerId, CreateRoomRequest createRoomRequest) {
    return roomRepository.save(
        new Room(createRoomRequest.getUnivId(),
            createRoomRequest.getPrice(),
            createRoomRequest.getRoomInfo(),
            createRoomRequest.getOption(),
            createRoomRequest.getLocation(),
            createRoomRequest.getDescription(),
            userRepository.findByUserId(sellerId).orElseThrow(UserNotFoundException::new)
        )).getRoomId();
  }

  /**
   * 방 정보 수정하기
   *
   * @param sellerId          집주인 ID
   * @param roomId            방ID
   * @param updateRoomRequest
   */
  @Transactional
  public void updateRoom(String sellerId, long roomId, UpdateRoomRequest updateRoomRequest) {
    Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
    if (!room.getSeller().getUserId().equals(sellerId)) {
      throw new NotYourRoomException();
    }
    room.updateRoom(
        updateRoomRequest.getUnivId(),
        updateRoomRequest.getPrice(),
        updateRoomRequest.getRoomInfo(),
        updateRoomRequest.getOption(),
        updateRoomRequest.getLocation(),
        updateRoomRequest.getDescription()
    );
  }

  /**
   * 방 삭제하기
   *
   * @param sellerId 집주인 ID
   * @param roomId   방ID
   */
  @Transactional
  public void deleteRoom(String sellerId, Long roomId) {
    if (roomRepository.existsByRoomIdAndSeller_UserId(roomId, sellerId)) {
      roomRepository.deleteById(roomId);
    } else {
      throw new NotYourRoomException();
    }
  }

  private Double[] parseRange(String ranges) {
    Double[] doubleRange = new Double[2];
    if (ranges != null) {
      String[] range = ranges.split("-");
      doubleRange[0] = Double.parseDouble(range[0]);
      doubleRange[1] = Double.parseDouble(range[1]);
    } else {
      doubleRange[0] = null;
      doubleRange[1] = null;
    }
    return doubleRange;
  }
}
