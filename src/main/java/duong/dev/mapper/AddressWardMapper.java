package duong.dev.mapper;

import duong.dev.dto.AddressWardDTO;
import duong.dev.entity.AddressWard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressWardMapper {
    @Autowired
    private ModelMapper mapper;
    public AddressWard convertToEntity(AddressWardDTO dto) {
        AddressWard entity = new AddressWard();
        mapper.map(dto, entity);
        return entity;
    }

    public AddressWardDTO convertToDTO(AddressWard entity) {
        AddressWardDTO dto = new AddressWardDTO();
        mapper.map(entity, dto);
        return dto;
    }
}
