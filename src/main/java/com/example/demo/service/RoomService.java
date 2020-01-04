package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.RecommandFilter;
import com.example.demo.model.Room;
import com.example.demo.model.RoomDetail;
import com.example.demo.repository.RecommandFilterRepository;
import com.example.demo.repository.RoomDetailRepository;
import com.example.demo.repository.RoomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoomService {

    private RecommandFilterRepository recommandFilterRepository;
    private RoomDetailRepository roomDetailRepository;
    private RoomRepository roomRepository;

    @Transactional
    public RoomlistWrapper getRoomlist(long Univid){
        RoomlistWrapper roomlistWrapper = new RoomlistWrapper();
        List<Room> RoomEntities = roomRepository.findAllByUnivid(Univid);
        List<RoomDto> RoomDtoList = new ArrayList<>();

        for ( Room RoomEntity : RoomEntities) {
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
        RecommandFilterDto recommandFilterDto1 = RecommandFilterDto.builder()
                .price(1)
                .scale(1)
                .structure(1)
                .floor(1)
                .distance(1)
                .grade(1)
                .build();
        recommandFilterRepository.save(recommandFilterDto1.toEntity());
        Optional<RecommandFilter> recommandFilterWrapper = recommandFilterRepository.findById(Univid);

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
        return roomlistWrapper;
    }
    @Transactional
    public RoomDetailWrapper getRoomDetail(long Roomid){

        Optional<Room> roomWrap = roomRepository.findById(Roomid);
        Room room = roomWrap.get();
        Optional<RoomDetail> roomDetailWrap = roomDetailRepository.findById(Roomid);
        RoomDetail roomDetail = roomDetailWrap.get();
        RoomDetailWrapper roomDetailWrapper = RoomDetailWrapper.builder()
                .id(room.getId())
                .pictureCount(roomDetail.getPictureCount())
                .sellerid(roomDetail.getSellerid())
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
        return roomDetailWrapper;
    }

    @Transactional
    public Long SaveRoom(long sellerid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

       GetRoomDetailWrapper getRoomDetailWrapper = new GetRoomDetailWrapper();
       getRoomDetailWrapper = objectMapper.readValue(json, GetRoomDetailWrapper.class);


     RoomDto roomDTO = RoomDto.builder()
             .structure(getRoomDetailWrapper.getRoomInformation().getStructure())
               .month(getRoomDetailWrapper.getRoomInformation().getPrice().getMonth())
               .adminExpenses(getRoomDetailWrapper.getRoomInformation().getPrice().getAdminExpenses())
                .deposit(getRoomDetailWrapper.getRoomInformation().getPrice().getDeposit())
                .floor(getRoomDetailWrapper.getRoomInformation().getFloor())
                .scale(getRoomDetailWrapper.getRoomInformation().getScale())
                .grade(2.5)
                .distance(distance(getRoomDetailWrapper.getLocation().getLat(),getRoomDetailWrapper.getLocation().getLng(),37.496281, 126.957358))
                .lat(getRoomDetailWrapper.getLocation().getLat())
                .lng(getRoomDetailWrapper.getLocation().getLng())
                .univid(getRoomDetailWrapper.getUnivid())
                .build();




        RoomDetailDto roomDetailDto = RoomDetailDto.builder()
                .pictureCount(getRoomDetailWrapper.getPictureCount())
                .sellerid(sellerid)
                .address(getRoomDetailWrapper.getRoomInformation().getAddress())
                .elevator(getRoomDetailWrapper.getExtraOption().isElevator())
                .park(getRoomDetailWrapper.getExtraOption().isPark())
                .cctv(getRoomDetailWrapper.getExtraOption().isCctv())
                .autoDoor(getRoomDetailWrapper.getExtraOption().isAutoDoor())
                .washingMachine(getRoomDetailWrapper.getExtraOption().isWashingMachine())
                .gasrange(getRoomDetailWrapper.getExtraOption().isGasrange())
                .refrigerator(getRoomDetailWrapper.getExtraOption().isRefrigerator())
                .airconditioner(getRoomDetailWrapper.getExtraOption().isAirconditioner())
                .busStation(getRoomDetailWrapper.getFacilities().isBusStation())
                .subwayStation(getRoomDetailWrapper.getFacilities().isSubwayStation())
                .description(getRoomDetailWrapper.getDescription())
                .build();

        roomRepository.save(roomDTO.toEntity()).getId();
        return roomDetailRepository.save(roomDetailDto.toEntity()).getId();


    }


    @Transactional
    public Long RewriteRoom(long Roomid,long Sellerid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        GetRoomDetailWrapper getRoomDetailWrapper;
        getRoomDetailWrapper = objectMapper.readValue(json, GetRoomDetailWrapper.class);

        RoomDto roomDTO = RoomDto.builder()
                .id(Roomid)
                .structure(getRoomDetailWrapper.getRoomInformation().getStructure())
                .month(getRoomDetailWrapper.getRoomInformation().getPrice().getMonth())
                .adminExpenses(getRoomDetailWrapper.getRoomInformation().getPrice().getAdminExpenses())
                .deposit(getRoomDetailWrapper.getRoomInformation().getPrice().getDeposit())
                .floor(getRoomDetailWrapper.getRoomInformation().getFloor())
                .scale(getRoomDetailWrapper.getRoomInformation().getScale())
                .distance(distance(getRoomDetailWrapper.getLocation().getLat(),getRoomDetailWrapper.getLocation().getLng(),37.496281, 126.957358))
                .grade(2.5)
                .lat(getRoomDetailWrapper.getLocation().getLat())
                .lng(getRoomDetailWrapper.getLocation().getLng())
                .univid(getRoomDetailWrapper.getUnivid())
                .build();


        RoomDetailDto roomDetailDto = RoomDetailDto.builder()
                .id(Roomid)
                .pictureCount(getRoomDetailWrapper.getPictureCount())
                .sellerid(Sellerid)
                .address(getRoomDetailWrapper.getRoomInformation().getAddress())
                .elevator(getRoomDetailWrapper.getExtraOption().isElevator())
                .park(getRoomDetailWrapper.getExtraOption().isPark())
                .cctv(getRoomDetailWrapper.getExtraOption().isCctv())
                .autoDoor(getRoomDetailWrapper.getExtraOption().isAutoDoor())
                .washingMachine(getRoomDetailWrapper.getExtraOption().isWashingMachine())
                .gasrange(getRoomDetailWrapper.getExtraOption().isGasrange())
                .refrigerator(getRoomDetailWrapper.getExtraOption().isRefrigerator())
                .airconditioner(getRoomDetailWrapper.getExtraOption().isAirconditioner())
                .busStation(getRoomDetailWrapper.getFacilities().isBusStation())
                .subwayStation(getRoomDetailWrapper.getFacilities().isSubwayStation())
                .description(getRoomDetailWrapper.getDescription())
                .build();
        roomRepository.save(roomDTO.toEntity()).getId();
        return roomDetailRepository.save(roomDetailDto.toEntity()).getId();
    }


    @Transactional
    public void DeleteRoom(Long Roomid) {
        roomRepository.deleteById(Roomid);
        roomDetailRepository.deleteById(Roomid);
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
