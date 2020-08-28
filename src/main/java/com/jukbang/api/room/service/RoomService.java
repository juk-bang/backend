package com.jukbang.api.room.service;

import com.jukbang.api.room.dto.RoomDetailWrapper;
import com.jukbang.api.room.dto.RoomlistWrapper;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public RoomlistWrapper getRoomlist(long Univid) {
/*        RoomlistWrapper roomlistWrapper = new RoomlistWrapper();
        List<Room> RoomEntities = roomRepository.findAllByUnividAndPermission(Univid, 1);
        List<RoomDto> RoomDtoList = new ArrayList<>();

        for (Room RoomEntity : RoomEntities) {
            RoomDto roomDTO = RoomDto.builder()
                    .id(RoomEntity.getId())
                    .structure(RoomEntity.getStructure())
                    .month(RoomEntity.getMonth())
                    .adminExpenses(RoomEntity.getAdminExpenses())
                    .deposit(RoomEntity.getDeposit())
                    .floor(RoomEntity.getFloor())
                    .scale(RoomEntity.getScale())
                    .grade(RoomEntity.getGrade())
                    .distance(RoomEntity.getDistance())
                    .lat(RoomEntity.getLat())
                    .lng(RoomEntity.getLng())
                    .build();
            Room room = new Room();
            room.setId(roomDTO.getId());

            RoomDtoList.add(roomDTO);
        }
        Optional<RecommandFilter> recommand = recommandFilterRepository.findByUnivid(Univid);
        RecommandFilter filter = recommand.get();
        RecommandFilterDto recommandFilterDto1 = RecommandFilterDto.builder()
                .id(filter.getId())
                .price(filter.getPrice())
                .scale(filter.getScale())
                .structure(filter.getStructure())
                .floor(filter.getFloor())
                .distance(filter.getDistance())
                .grade(filter.getGrade())
                .build();
        recommandFilterRepository.save(recommandFilterDto1.toEntity());
        Optional<RecommandFilter> recommandFilterWrapper = recommandFilterRepository.findById(filter.getId());

        RecommandFilter recommandFilter = recommandFilterWrapper.get();

        RecommandFilterDto recommandFilterDto = RecommandFilterDto.builder()
                .id(recommandFilter.getId())
                .price(recommandFilter.getPrice())
                .scale(recommandFilter.getScale())
                .structure(recommandFilter.getStructure())
                .floor(recommandFilter.getFloor())
                .distance(recommandFilter.getDistance())
                .grade(recommandFilter.getGrade())
                .build();

        roomlistWrapper.setRoom(RoomDtoList);
        roomlistWrapper.setRecommandFilter(recommandFilterDto);
        return roomlistWrapper;*/
        return null;
    }

    @Transactional
    public List<Room> SellerRoomlist(String sellerId) {
/*        List<Room> RoomEntities = new ArrayList<>();
        List<RoomDetail> RoomDetailEntities = roomDetailRepository.findAllBySellerId(sellerId);
        for (RoomDetail RoomDetailEntity : RoomDetailEntities) {

            Optional<Room> room = roomRepository.findById(RoomDetailEntity.getId());
            RoomEntities.add(room.get());
        }


        return RoomEntities;*/
        return null;
    }

    @Transactional
    public RoomDetailWrapper getRoomDetail(long roomId) {
/*
        Optional<Room> roomWrap = roomRepository.findById(roomId);
        Room room = roomWrap.get();
        Optional<RoomDetail> roomDetailWrap = roomDetailRepository.findById(roomId);
        RoomDetail roomDetail = roomDetailWrap.get();
        RoomDetailWrapper roomDetailWrapper = RoomDetailWrapper.builder()
                .id(room.getId())
                .pictureCount(roomDetail.getPictureCount())
                .sellerId(roomDetail.getSellerId())
                .address(roomDetail.getAddress())
                .structure(room.getStructure())
                .scale(room.getScale())
                .floor(room.getFloor())
                .distance(room.getDistance())
                .month(room.getMonth())
                .adminExpenses(room.getAdminExpenses())
                .deposit(room.getDeposit())
                .grade(room.getGrade())
                .elevator(roomDetail.isElevator())
                .park(roomDetail.isPark())
                .cctv(roomDetail.isCctv())
                .autoDoor(roomDetail.isAutoDoor())
                .washingMachine(roomDetail.isWashingMachine())
                .gasrange(roomDetail.isGasrange())
                .refrigerator(roomDetail.isRefrigerator())
                .airconditioner(roomDetail.isAirconditioner())
                .description(roomDetail.getDescription())
                .lat(room.getLat())
                .lng(room.getLng())
                .busStation(roomDetail.isBusStation())
                .subwayStation(roomDetail.isSubwayStation())
                .build();
        return roomDetailWrapper;*/
        return null;
    }

    @Transactional
    public Long createRoom(String sellerId, CreateRoomRequest createRoomRequest) {
     /*   double grade, reversegrade;
        reversegrade = createRoomRequest.getRoomInformation().getPrice().getMonth() / 40.0 + createRoomRequest.getRoomInformation().getPrice().getAdminExpenses() / 5.0 + createRoomRequest.getRoomInformation().getPrice().getDeposit() / 700.0;
        if (!createRoomRequest.getExtraOption().isAirconditioner()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isAutoDoor()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isCctv()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isElevator()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isGasrange()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isPark()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isRefrigerator()) reversegrade += 0.3;
        if (!createRoomRequest.getExtraOption().isWashingMachine()) reversegrade += 0.3;
        grade = 10.0 - reversegrade;
        RoomDto roomDTO = RoomDto.builder()
                .structure(createRoomRequest.getRoomInformation().getStructure())
                .month(createRoomRequest.getRoomInformation().getPrice().getMonth())
                .adminExpenses(createRoomRequest.getRoomInformation().getPrice().getAdminExpenses())
                .deposit(createRoomRequest.getRoomInformation().getPrice().getDeposit())
                .floor(createRoomRequest.getRoomInformation().getFloor())
                .scale(createRoomRequest.getRoomInformation().getScale())
                .grade(reversegrade)
                .distance(distance(createRoomRequest.getLocation().getLat(), createRoomRequest.getLocation().getLng(), 37.496281, 126.957358))
                .lat(createRoomRequest.getLocation().getLat())
                .lng(createRoomRequest.getLocation().getLng())
                .univid(createRoomRequest.getUnivId())
                .permission(0)
                .build();

        RoomDetailDto roomDetailDto = RoomDetailDto.builder()
                .pictureCount(createRoomRequest.getPictureCount())
                .sellerId(sellerId)
                .address(createRoomRequest.getRoomInformation().getAddress())
                .elevator(createRoomRequest.getExtraOption().isElevator())
                .park(createRoomRequest.getExtraOption().isPark())
                .cctv(createRoomRequest.getExtraOption().isCctv())
                .autoDoor(createRoomRequest.getExtraOption().isAutoDoor())
                .washingMachine(createRoomRequest.getExtraOption().isWashingMachine())
                .gasrange(createRoomRequest.getExtraOption().isGasrange())
                .refrigerator(createRoomRequest.getExtraOption().isRefrigerator())
                .airconditioner(createRoomRequest.getExtraOption().isAirconditioner())
                .busStation(createRoomRequest.getFacilities().isBusStation())
                .subwayStation(createRoomRequest.getFacilities().isSubwayStation())
                .description(createRoomRequest.getDescription())
                .build();

        RecommandFilter filter;
        if (!recommandFilterRepository.findByUnivid(createRoomRequest.getUnivId()).isPresent()) {
            filter = new RecommandFilter();
            filter.setPrice(createRoomRequest.getRoomInformation().getPrice().getMonth());
            filter.setScale(createRoomRequest.getRoomInformation().getScale());
            filter.setStructure(1);
            filter.setFloor(createRoomRequest.getRoomInformation().getFloor());
            filter.setDistance(roomDTO.getDistance());
            filter.setGrade(roomDTO.getGrade());
            filter.setUnivid(roomDTO.getUnivid());
        } else {
            Optional<RecommandFilter> recommandFilter = recommandFilterRepository.findByUnivid(createRoomRequest.getUnivId());
            filter = recommandFilter.get();
            List<Room> RoomEntities = roomRepository.findAllByUnivid(createRoomRequest.getUnivId());
            int n = RoomEntities.size();
            filter.setPrice((filter.getPrice() * n + roomDTO.getMonth()) / (n + 1));
            filter.setScale((filter.getScale() * n + roomDTO.getScale()) / (n + 1));
            filter.setStructure(1);
            filter.setFloor((filter.getFloor() * n + roomDTO.getFloor()) / (n + 1));
            filter.setDistance((filter.getDistance() * n + roomDTO.getDistance()) / (n + 1));
            filter.setGrade((filter.getGrade() * n + roomDTO.getGrade()) / (n + 1));
        }

        recommandFilterRepository.save(filter).getId();
        roomRepository.save(roomDTO.toEntity()).getId();
        return roomDetailRepository.save(roomDetailDto.toEntity()).getId();*/
        return (long)0;
    }


    @Transactional
    public void updateRoom(String sellerId, long roomId, UpdateRoomRequest updateRoomRequest) {
/*        double grade, reversegrade;

        reversegrade = updateRoomRequest.getRoomInformation().getPrice().getMonth() / 40.0 + updateRoomRequest.getRoomInformation().getPrice().getAdminExpenses() / 5.0 + updateRoomRequest.getRoomInformation().getPrice().getDeposit() / 700.0;
        if (!updateRoomRequest.getExtraOption().isAirconditioner()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isAutoDoor()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isCctv()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isElevator()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isGasrange()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isPark()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isRefrigerator()) reversegrade += 0.3;
        if (!updateRoomRequest.getExtraOption().isWashingMachine()) reversegrade += 0.3;
        grade = 10.0 - reversegrade;

        RoomDto roomDTO = RoomDto.builder()
                .id(roomId)
                .structure(updateRoomRequest.getRoomInformation().getStructure())
                .month(updateRoomRequest.getRoomInformation().getPrice().getMonth())
                .adminExpenses(updateRoomRequest.getRoomInformation().getPrice().getAdminExpenses())
                .deposit(updateRoomRequest.getRoomInformation().getPrice().getDeposit())
                .floor(updateRoomRequest.getRoomInformation().getFloor())
                .scale(updateRoomRequest.getRoomInformation().getScale())
                .distance(distance(updateRoomRequest.getLocation().getLat(), updateRoomRequest.getLocation().getLng(), 37.496281, 126.957358))
                .grade(grade)
                .lat(updateRoomRequest.getLocation().getLat())
                .lng(updateRoomRequest.getLocation().getLng())
                .univid(updateRoomRequest.getUnivId())
                .build();


        RoomDetailDto roomDetailDto = RoomDetailDto.builder()
                .id(roomId)
                .pictureCount(updateRoomRequest.getPictureCount())
                .sellerId(sellerId)
                .address(updateRoomRequest.getRoomInformation().getAddress())
                .elevator(updateRoomRequest.getExtraOption().isElevator())
                .park(updateRoomRequest.getExtraOption().isPark())
                .cctv(updateRoomRequest.getExtraOption().isCctv())
                .autoDoor(updateRoomRequest.getExtraOption().isAutoDoor())
                .washingMachine(updateRoomRequest.getExtraOption().isWashingMachine())
                .gasrange(updateRoomRequest.getExtraOption().isGasrange())
                .refrigerator(updateRoomRequest.getExtraOption().isRefrigerator())
                .airconditioner(updateRoomRequest.getExtraOption().isAirconditioner())
                .busStation(updateRoomRequest.getFacilities().isBusStation())
                .subwayStation(updateRoomRequest.getFacilities().isSubwayStation())
                .description(updateRoomRequest.getDescription())
                .build();
        roomRepository.save(roomDTO.toEntity());
        return roomDetailRepository.save(roomDetailDto.toEntity()).getId();*/
    }

    @Transactional
    public List<Room> wantpermit(long Univid) {
/*
        return roomRepository.findAllByUnividAndPermission(Univid, 0);
*/
        return null;
    }

    @Transactional
    public void permit(long Univid, long id) {
/*        Optional<Room> room = roomRepository.findById(id);
        Room target = room.get();
        target.setPermission(1);
        roomRepository.save(target);*/
    }

    @Transactional
    public void dontpermit(long Univid, long id) {
/*        Optional<Room> room = roomRepository.findById(id);
        Room target = room.get();
        target.setPermission(2);
        roomRepository.save(target);*/
    }

    @Transactional
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }


    public double distance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;


        dist = dist * 1609.344;


        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
