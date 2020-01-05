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
import org.springframework.web.bind.annotation.PathVariable;

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
        List<Room> RoomEntities = roomRepository.findAllByUnividAndPermission(Univid,1);
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
        Optional<RecommandFilter> recommand = recommandFilterRepository.findByUnivid(Univid);
        RecommandFilter filter = recommand.get();
        RecommandFilterDto recommandFilterDto1 = RecommandFilterDto.builder()
                .price(filter.getPrice())
                .scale(filter.getScale())
                .structure(filter.getStructure())
                .floor(filter.getFloor())
                .distance(filter.getDistance())
                .grade(filter.getGrade())
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
    public List<Room> SellerRoomlist(long sellerid){
        List<Room> RoomEntities = new ArrayList<>();
        List<RoomDetail> RoomDetailEntities = roomDetailRepository.findAllBySellerid(sellerid);
        for ( RoomDetail RoomDetailEntity : RoomDetailEntities) {

            Optional<Room> room = roomRepository.findById(RoomDetailEntity.getId());
            RoomEntities.add(room.get());
        }


        return RoomEntities;

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
    public Long SaveRoom(String sellerid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        GetRoomDetailWrapper getRoomDetailWrapper = new GetRoomDetailWrapper();
        getRoomDetailWrapper = objectMapper.readValue(json, GetRoomDetailWrapper.class);
        double grade,reversegrade;
        reversegrade = getRoomDetailWrapper.getRoomInformation().getPrice().getMonth()/40.0+getRoomDetailWrapper.getRoomInformation().getPrice().getAdminExpenses()/5.0+getRoomDetailWrapper.getRoomInformation().getPrice().getDeposit()/700.0;
        if(!getRoomDetailWrapper.getExtraOption().isAirconditioner())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isAutoDoor())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isCctv())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isElevator())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isGasrange())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isPark())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isRefrigerator())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isWashingMachine())reversegrade+=0.3;
        grade = 10.0-reversegrade;
         RoomDto roomDTO = RoomDto.builder()
                 .structure(getRoomDetailWrapper.getRoomInformation().getStructure())
                   .month(getRoomDetailWrapper.getRoomInformation().getPrice().getMonth())
                   .adminExpenses(getRoomDetailWrapper.getRoomInformation().getPrice().getAdminExpenses())
                    .deposit(getRoomDetailWrapper.getRoomInformation().getPrice().getDeposit())
                    .floor(getRoomDetailWrapper.getRoomInformation().getFloor())
                    .scale(getRoomDetailWrapper.getRoomInformation().getScale())
                    .grade(reversegrade)
                    .distance(distance(getRoomDetailWrapper.getLocation().getLat(),getRoomDetailWrapper.getLocation().getLng(),37.496281, 126.957358))
                    .lat(getRoomDetailWrapper.getLocation().getLat())
                    .lng(getRoomDetailWrapper.getLocation().getLng())
                    .univid(getRoomDetailWrapper.getUnivid())
                    .permission(0)
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

        RecommandFilter filter;
        if(!recommandFilterRepository.findByUnivid(getRoomDetailWrapper.getUnivid()).isPresent()){
            filter = new RecommandFilter();
            filter.setPrice(getRoomDetailWrapper.getRoomInformation().getPrice().getMonth());
            filter.setScale(getRoomDetailWrapper.getRoomInformation().getScale());
            filter.setStructure(1);
            filter.setFloor(getRoomDetailWrapper.getRoomInformation().getFloor());
            filter.setDistance(roomDTO.getDistance());
            filter.setGrade(roomDTO.getGrade());
            filter.setUnivid(roomDTO.getUnivid());
        }
        else{
            Optional<RecommandFilter> recommandFilter = recommandFilterRepository.findByUnivid(getRoomDetailWrapper.getUnivid());
            filter = recommandFilter.get();
            List<Room> RoomEntities = roomRepository.findAllByUnivid(getRoomDetailWrapper.getUnivid());
            int n = RoomEntities.size();
            filter.setPrice((filter.getPrice()*n+ roomDTO.getMonth())/(n+1));
            filter.setScale((filter.getScale()*n+roomDTO.getScale())/(n+1));
            filter.setStructure(1);
            filter.setFloor((filter.getFloor()*n+roomDTO.getFloor())/(n+1));
            filter.setDistance((filter.getDistance()*n+roomDTO.getDistance())/(n+1));
            filter.setGrade((filter.getGrade()*n+roomDTO.getGrade())/(n+1));
        }

        recommandFilterRepository.save(filter).getId();
        roomRepository.save(roomDTO.toEntity()).getId();
        return roomDetailRepository.save(roomDetailDto.toEntity()).getId();


    }


    @Transactional
    public Long RewriteRoom(String Sellerid,long Roomid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        GetRoomDetailWrapper getRoomDetailWrapper;
        getRoomDetailWrapper = objectMapper.readValue(json, GetRoomDetailWrapper.class);
        double grade,reversegrade;
        reversegrade = getRoomDetailWrapper.getRoomInformation().getPrice().getMonth()/40.0+getRoomDetailWrapper.getRoomInformation().getPrice().getAdminExpenses()/5.0+getRoomDetailWrapper.getRoomInformation().getPrice().getDeposit()/700.0;
        if(!getRoomDetailWrapper.getExtraOption().isAirconditioner())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isAutoDoor())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isCctv())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isElevator())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isGasrange())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isPark())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isRefrigerator())reversegrade+=0.3;
        if(!getRoomDetailWrapper.getExtraOption().isWashingMachine())reversegrade+=0.3;
        grade = 10.0-reversegrade;

        RoomDto roomDTO = RoomDto.builder()
                .id(Roomid)
                .structure(getRoomDetailWrapper.getRoomInformation().getStructure())
                .month(getRoomDetailWrapper.getRoomInformation().getPrice().getMonth())
                .adminExpenses(getRoomDetailWrapper.getRoomInformation().getPrice().getAdminExpenses())
                .deposit(getRoomDetailWrapper.getRoomInformation().getPrice().getDeposit())
                .floor(getRoomDetailWrapper.getRoomInformation().getFloor())
                .scale(getRoomDetailWrapper.getRoomInformation().getScale())
                .distance(distance(getRoomDetailWrapper.getLocation().getLat(),getRoomDetailWrapper.getLocation().getLng(),37.496281, 126.957358))
                .grade(grade)
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
    public List<Room> wantpermit(long Univid){
        return roomRepository.findAllByUnividAndPermission(Univid,0);

    }

    @Transactional
    public void permit(long Univid, long id){
        Optional<Room> room = roomRepository.findById(id);
        Room target = room.get();
        target.setPermission(1);
        roomRepository.save(target);
    }

    @Transactional
    public void dontpermit(long Univid, long id){
        Optional<Room> room = roomRepository.findById(id);
        Room target = room.get();
        target.setPermission(2);
        roomRepository.save(target);
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
