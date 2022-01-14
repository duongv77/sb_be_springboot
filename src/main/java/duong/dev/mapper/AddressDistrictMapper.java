package duong.dev.mapper;

import duong.dev.dto.AddressDistrictDTO;
import duong.dev.entity.AddressDistrict;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDistrictMapper {
    @Autowired
    private ModelMapper mapper;
    public AddressDistrict convertToEntity(AddressDistrictDTO dto) {
        AddressDistrict entity = new AddressDistrict();
        mapper.map(dto, entity);
        return entity;
    }

    public AddressDistrictDTO convertToDTO(AddressDistrict entity) {
        AddressDistrictDTO dto = new AddressDistrictDTO();
        mapper.map(entity, dto);
        return dto;
    }
}
