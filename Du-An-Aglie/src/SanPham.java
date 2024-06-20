

public class SanPham {
    private String MaSP;
    private String MaLoaiSP;
    private String TenSp;
    private String TenLoaiSp;
    private double GiaSP;
    private int SoLuong;
    private String DanhGia;
    private String Hinh;

    public SanPham() {
    }

    public SanPham(String MaSP, String MaLoaiSP, String TenSp, String TenLoaiSp, double GiaSP, int SoLuong, String DanhGia, String Hinh) {
        this.MaSP = MaSP;
        this.MaLoaiSP = MaLoaiSP;
        this.TenSp = TenSp;
        this.TenLoaiSp = TenLoaiSp;
        this.GiaSP = GiaSP;
        this.SoLuong = SoLuong;
        this.DanhGia = DanhGia;
        this.Hinh = Hinh;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String TenSp) {
        this.TenSp = TenSp;
    }

    public String getTenLoaiSp() {
        return TenLoaiSp;
    }

    public void setTenLoaiSp(String TenLoaiSp) {
        this.TenLoaiSp = TenLoaiSp;
    }

    public double getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(double GiaSP) {
        this.GiaSP = GiaSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(String DanhGia) {
        this.DanhGia = DanhGia;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }


    
}
