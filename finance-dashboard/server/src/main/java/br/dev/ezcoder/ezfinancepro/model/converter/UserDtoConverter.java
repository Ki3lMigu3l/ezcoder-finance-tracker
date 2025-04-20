package br.dev.ezcoder.ezfinancepro.model.converter;

import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseDTO;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseUpdateDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.User;

public interface UserDtoConverter {
    UserResponseDTO toReponse (User user);
    UserResponseUpdateDTO toUpdateResponse (User user);
}
