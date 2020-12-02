package com.jukbang.api.admin.service;

import com.jukbang.api.admin.dto.PermitListDto;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.room.repository.RoomRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RoomRepository roomRepository;

    @Transactional
    public List<PermitListDto> wantPermit() {
        List<Room> rooms =  roomRepository.findAllByPermission(0);
        List<PermitListDto> permitListDtos = new ArrayList<PermitListDto>();
        for(Room room: rooms){
            permitListDtos.add(new PermitListDto(room.getUnivId(),room.getRoomId(),room.getRoomInfo().getRoomName()));
        }
        return permitListDtos;
    }

    @Transactional
    public void permit(long univId, long id) {
        roomRepository.findById(id).orElseThrow(RoomNotFoundException::new).permitRoom();
    }

    @Transactional
    public void reject(long univId, long id) {
        roomRepository.findById(id).orElseThrow(RoomNotFoundException::new).rejectRoom();
    }
}
