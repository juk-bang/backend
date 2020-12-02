package com.jukbang.api.admin.response;

import com.jukbang.api.admin.dto.PermitListDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRoomsResponse {
  private List<PermitListDto> rooms;
}
