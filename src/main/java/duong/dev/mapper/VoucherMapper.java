package duong.dev.mapper;

import duong.dev.dto.VoucherDTO;
import duong.dev.entity.Voucher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {
    @Autowired
    private ModelMapper mapper;

    public Voucher convertToEntity(VoucherDTO dto) {
        Voucher entity = new Voucher();
        mapper.map(dto, entity);
        return entity;
    }

    public VoucherDTO convertToDTO(Voucher entity) {
        VoucherDTO dto = new VoucherDTO();
        mapper.map(entity, dto);
        return dto;
    }
}
