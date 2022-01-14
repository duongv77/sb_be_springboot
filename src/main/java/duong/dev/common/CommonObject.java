package duong.dev.common;

import duong.dev.entity.Categorie;
import duong.dev.entity.Role;
import duong.dev.entity.StatusOrder;

public class CommonObject {
	public static final Role ROLE_USER = new Role(3, "USER", null);
	public static final StatusOrder STATUS_ORDER_CHO_XAC_NHAN = new StatusOrder(1, "Chờ xác nhận");
	public static final StatusOrder STATUS_ORDER_DA_XAC_NHAN = new StatusOrder(2, "Đã xác nhận");
	public static final StatusOrder STATUS_ORDER_DANG_GIAO_HANG = new StatusOrder(3, "Đang giao hàng");
	public static final StatusOrder STATUS_ORDER_DA_GIAO_HANG = new StatusOrder(4, "Đã giao hàng");
	public static final StatusOrder STATUS_ORDER_DA_HUY = new StatusOrder(5, "Đã hủy");
	public static final StatusOrder STATUS_ORDER_YEU_CAU_TRA_HANG = new StatusOrder(6, "Trả hàng");
	public static final Categorie CATEGORY_KHONG_XAC_DINH = new Categorie(14, "Không xác định", null, null, null, null, null, null);
}
