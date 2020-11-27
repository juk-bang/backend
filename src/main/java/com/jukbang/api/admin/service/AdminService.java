package com.jukbang.api.admin.service;

import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.room.repository.RoomRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RoomRepository roomRepository;

    @Transactional
    public List<Room> wantPermit() {
        return roomRepository.findAllByPermission(0);
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
