package duong.dev.service;

import duong.dev.dto.AddressDistrictDTO;
import duong.dev.dto.AddressProvinceDTO;
import duong.dev.dto.AddressWardDTO;

import java.util.List;

public interface AddressApiInterface {
    public List<AddressProvinceDTO> findAllProvince();
    public List<AddressDistrictDTO> findAllDistricts();
    public List<AddressWardDTO> findAllWard();
    public List<AddressWardDTO> findAllWardByProvince(Integer id);
    public List<AddressWardDTO> findAllWardByDistricts(Integer id);
    public List<AddressDistrictDTO> findAllDistrictsByProvince(Integer id);
}
