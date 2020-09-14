package com.jukbang.api.admin.service;

import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RoomRepository roomRepository;

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
}
