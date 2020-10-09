package com.jukbang.api.configs;

import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Option;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import com.jukbang.api.security.request.SignUpRequest;
import com.jukbang.api.security.service.AuthService;
import com.jukbang.api.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class InitTestData implements ApplicationListener<ApplicationStartedEvent> {

    private final AuthService authService;
    private final RoomService roomService;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        for (int i = 0; i < 5; i++)
            generateUser(i, UserRole.ROLE_LANDLORD);
        for (int i = 5; i < 10; i++)
            generateUser(i, UserRole.ROLE_STUDENT);
        for (int i = 0; i < 5; i++)
            generateRoom(i);
    }

    private void generateUser(int index, UserRole role) {
        this.authService.signUp(
                SignUpRequest.builder()
                        .id("TestUser" + index)
                        .password("password")
                        .univId(1)
                        .role(role)
                        .build());
    }

    private void generateRoom(int index) {
        this.roomService.registerRoom(
                "TestUser" + index,
                CreateRoomRequest.builder()
                        .univId(1)
                        .price(new Price(20,2,100.5))
                        .roomInfo(new RoomInfo(index + "번 방", 10, 2, 3))
                        .option(new Option(true,true,true,true,true,true,true,true))
                        .location(new Location("서울시 어쩌구 저쩌동", 37.494647, 126.959809))
                        .description(index + "번 방입니다.")
                        .build()
        );
    }
}