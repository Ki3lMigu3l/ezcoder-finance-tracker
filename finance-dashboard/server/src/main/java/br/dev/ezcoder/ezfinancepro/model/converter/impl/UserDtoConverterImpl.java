package br.dev.ezcoder.ezfinancepro.model.converter.impl;

import br.dev.ezcoder.ezfinancepro.model.converter.UserDtoConverter;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseDTO;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseUpdateDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {
    @Override
    public UserResponseDTO toReponse(User user) {
        return UserResponseDTO.entityToResponse(user);
    }

    @Override
    public UserResponseUpdateDTO toUpdateResponse(User user) {
        return UserResponseUpdateDTO.entityToResponse(user);
    }
}
