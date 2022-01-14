package duong.dev.mapper;

import duong.dev.dto.AddressProvinceDTO;
import duong.dev.entity.AddressProvince;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressProvinceMapper {
    @Autowired
    private ModelMapper mapper;
    public AddressProvince convertToEntity(AddressProvinceDTO dto) {
        AddressProvince entity = new AddressProvince();
        mapper.map(dto, entity);
        return entity;
    }

    public AddressProvinceDTO convertToDTO(AddressProvince entity) {
        AddressProvinceDTO dto = new AddressProvinceDTO();
        mapper.map(entity, dto);
        return dto;
    }
}
