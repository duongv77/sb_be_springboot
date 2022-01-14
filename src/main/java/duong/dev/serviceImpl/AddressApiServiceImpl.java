package duong.dev.serviceImpl;

import duong.dev.dto.AddressDistrictDTO;
import duong.dev.dto.AddressProvinceDTO;
import duong.dev.dto.AddressWardDTO;
import duong.dev.entity.AddressDistrict;
import duong.dev.entity.AddressProvince;
import duong.dev.entity.AddressWard;
import duong.dev.mapper.AddressDistrictMapper;
import duong.dev.mapper.AddressProvinceMapper;
import duong.dev.mapper.AddressWardMapper;
import duong.dev.repository.AddressDistrictRepository;
import duong.dev.repository.AddressProvinceRepository;
import duong.dev.repository.AddressWardRepository;
import duong.dev.service.AddressApiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressApiServiceImpl implements AddressApiInterface {
    @Autowired
    private AddressWardRepository wardRepo;
    @Autowired
    private AddressDistrictRepository districtRepo;
    @Autowired
    private AddressProvinceRepository provinceRepo;
    @Autowired
    private AddressWardMapper wardMapper;
    @Autowired
    private AddressDistrictMapper districtMapper;
    @Autowired
    private AddressProvinceMapper provinceMapper;

    @Override
    public List<AddressDistrictDTO> findAllDistricts() {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(1, 50, sort);
        return convertListDistrictDTO(districtRepo.findAllCustom(pageable));
    }

    @Override
    public List<AddressProvinceDTO> findAllProvince() {
        Sort sort = Sort.by("name").ascending();
        return convertListProvinceTO(provinceRepo.findAll(sort));
    }

    @Override
    public List<AddressWardDTO> findAllWard() {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(1, 50, sort);
        return convertListWardTO(wardRepo.findAllCustom(pageable));
    }

    @Override
    public List<AddressWardDTO> findAllWardByProvince(Integer id) {
        return convertListWardTO(wardRepo.findByProvince(id));
    }

    @Override
    public List<AddressWardDTO> findAllWardByDistricts(Integer id) {
        return convertListWardTO(wardRepo.findByDistrict(id));
    }

    @Override
    public List<AddressDistrictDTO> findAllDistrictsByProvince(Integer id) {
        return convertListDistrictDTO(districtRepo.findByProvince(id));
    }

    public List<AddressDistrictDTO> convertListDistrictDTO(List<AddressDistrict> list){
        return list.stream().map(elm->{
            return districtMapper.convertToDTO(elm);
        }).collect(Collectors.toList());
    }
    public List<AddressProvinceDTO> convertListProvinceTO(List<AddressProvince> list){
        return list.stream().map(elm->{
            return provinceMapper.convertToDTO(elm);
        }).collect(Collectors.toList());
    }
    public List<AddressWardDTO> convertListWardTO(List<AddressWard> list){
        return list.stream().map(elm->{
            return wardMapper.convertToDTO(elm);
        }).collect(Collectors.toList());
    }
}
