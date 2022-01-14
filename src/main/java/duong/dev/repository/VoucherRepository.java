package duong.dev.repository;


import duong.dev.entity.Voucher;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    public Optional<Voucher> findByVoucherCode(String code);

    @Query("SELECT v FROM Voucher v WHERE v.voucherCode || v.id  like %:keyword%")
	public List<Voucher> searchVoucher(@Param("keyword") String keyword, Sort sort );

    @Query("select v from Voucher v where v.status = ?1")
    public List<Voucher> findAllVouchersByStatus(Integer status);

}
