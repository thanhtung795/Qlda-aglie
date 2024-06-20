create database QLQUANCAFE
go

use QLQUANCAFE
go

create table KhachHang(
MaKH nvarchar(10) primary key,
TenKH nvarchar(50) ,
ngSinh date,
SDT nvarchar(12),
gioiTinh bit,
DiaChi nvarchar(50)
)
go

create table NhanVien(
MaNV nvarchar(10) primary key,
TenNV nvarchar(50),
SDT nvarchar(12),
DiaChi nvarchar(50),
ChucVu nvarchar(20)
)
go

create table LoaiSP(
MaloaiSP nvarchar(10) primary key,
TenLoaiSP nvarchar(50)
)
go

create table KhuyenMai(
MaKM nvarchar(10) primary key,
NgayTao date,
HanSuDung date,
)
go

create table SanPham(
MaSP nvarchar(10) primary key,
MaLoaiSP nvarchar(10),
TenSP nvarchar(50),
GiaSP money,
SoLuong int,
DanhGia nvarchar(50),
Hinh nvarchar(150),
constraint fk_SP_LSP foreign key (MaLoaiSP) references LoaiSP (MaLoaiSP)
)
go

create table HoaDon(
MaHD nvarchar(10) primary key,
MaSP nvarchar(10),
MaKH nvarchar(10),
MaNV nvarchar(10),
ngayLapHD date,
kieuThanhToan nvarchar(50),
thanhTien money,
constraint fk_HD_KH foreign key (MaKH) references KhachHang (MaKH),
constraint fk_HD_NV foreign key (MaNV) references NhanVien (MaNV)
)
go

create table HoaDonChiTiet(
MaHD nvarchar(10),
MaSP nvarchar(10),
SoLuong int,
primary key(MaHD,MaSP),
constraint fk_HDCT_SP foreign key (MaSP) references SanPham (MaSP),
constraint fk_HDCT_HD foreign key (MaHD) references HoaDon (MaHD)
)
go

create table KhuyenMaiChiTiet(
MaKM nvarchar(10),
MaHD nvarchar(10),
TenKhuyenMai nvarchar(50),
UuDai nvarchar(50),
primary key (MaKM,MaHD),
constraint fk_KMCT_KM foreign key (MaKM) references KhuyenMai (MaKM),
constraint fk_KMCT_HD foreign key (MaHD) references HoaDon (MaHD)
)
go

-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (MaKH, TenKH, ngSinh, SDT, gioiTinh, DiaChi)
VALUES
('KH001', N'Nguyễn Văn Tý', '1990-01-01', '0123456789', 1, N'123 Chu Văn Trinh, Quận Phú Nhuận'),
('KH002', N'Tran Thi Nguyệt', '1995-05-15', '0987654321', 0, N'456 Đinh Tiên Hoàng, Quận 5'),
('KH003', N'Le Van Chính', '1988-12-10', '0345678901', 1, N'789 Trần Noãn, Quận 2'),
('KH004', N'Pham Thi Định', '1992-03-20', '0765432109', 0, N'101 Phù Đổng Thiên Vương, Quận 1'),
('KH005', N'Hoang Van Nam', '1998-08-25', '0234567890', 1, N'202 Tô Kí, Quận 12');

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNV, TenNV, SDT, DiaChi, ChucVu)
VALUES
('NV001', N'Tran Van Vinh', '0987654321', N'123 Quang Trung, Quận Gò Vấp', N'Quan ly'),
('NV002', N'Nguyen Thi Yến', '0123456789', N'456 Lê Đức Thọ, Quận Gò Vấp', N'Nhan vien'),
('NV003', N'Le Van Việt', '0345678901', N'789 Cộng Hòa, Quận Tân Bình', N'Nhan vien'),
('NV004', N'Pham Van Kiệt', '0765432109', N'101 Bờ Bao Tân Thắng, Quận Tân Phú', N'Quan ly'),
('NV005', N'Hoang Thi Minh Thư', '0234567890', N'202 Lý Thường Kiệt, Quận 10', N'Nhan vien');

-- Thêm dữ liệu vào bảng LoaiSP
INSERT INTO LoaiSP (MaloaiSP, TenLoaiSP)
VALUES
('LSP001', N'Coffee'),
('LSP002', N'Trà'),
('LSP003', N'Bánh ngọt'),
('LSP004', N'Nước ép & sinh tố');


-- Thêm dữ liệu vào bảng KhuyenMai
INSERT INTO KhuyenMai (MaKM, NgayTao, HanSuDung)
VALUES
('KM001', '2023-10-31', '2023-12-15'),
('KM002', '2023-10-31', '2023-12-15'),
('KM003', '2023-10-31', '2023-12-15'),
('KM004', '2023-10-31', '2023-12-15'),
('KM005', '2023-10-31', '2023-12-15');

-- Thêm dữ liệu vào bảng SanPham
INSERT INTO SanPham (MaSP, MaLoaiSP, TenSP, GiaSP, SoLuong, DanhGia, Hinh)
VALUES
('SP001', 'LSP001', N'Capuchino', 25000, 100, N'Tốt', 'Capuchino.jpg'),
('SP002', 'LSP002', N'Trà trái cây nhiệt đới', 35000, 50, N'Tuyệt', 'tra.jpg'),
('SP003', 'LSP003', N'Bánh flan', 20000, 80, N'Yummy', 'banh_flan.jpg'),
('SP004', 'LSP004', N'Sinh tố dâu', 50000, 30, N'Ngon', 'dau.jpg'),
('SP005', 'LSP001', N'Cofe đen', 100000, 20, N'Hợp gu', 'cafe.jpg');

-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (MaHD, MaSP, MaKH, MaNV, ngayLapHD, kieuThanhToan, thanhTien)
VALUES
('HD001', 'SP001', 'KH001', 'NV001', '2023-11-20', N'cash', 25000),
('HD002', 'SP002', 'KH002', 'NV002', '2023-11-20', N'ATM', 35000),
('HD003', 'SP003', 'KH003', 'NV003', '2023-11-20', N'credit card', 20000),
('HD004', 'SP004', 'KH004', 'NV004', '2023-11-20', N'cash', 50000),
('HD005', 'SP005', 'KH005', 'NV005', '2023-11-20', N'ATM', 100000);

-- Thêm dữ liệu vào bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES
('HD001', 'SP001', 2),
('HD002', 'SP002', 1),
('HD003', 'SP003', 3),
('HD004', 'SP004', 1),
('HD005', 'SP005', 2);

-- Thêm dữ liệu vào bảng KhuyenMaiChiTiet
INSERT INTO KhuyenMaiChiTiet (MaKM, MaHD, TenKhuyenMai, UuDai)
VALUES
('KM001', 'HD001', 'Giam gia 10%', '10%'),
('KM002', 'HD002', 'Giam gia 20%', '20%'),
('KM003', 'HD003', 'Giam gia 15%', '15%'),
('KM004', 'HD004', 'Giam gia 25%', '25%'),
('KM005', 'HD005', 'Giam gia 30%', '30%');

go


create table Remember(
	Username varchar(50),
	Password varchar(50),
	Remenber bit
	primary key (Username)
)
go

drop table users

create table Users(
	Username varchar(50),
	Password varchar(50),
	role varchar(50),
	primary key (Username)
)

select * from Users

insert into Users values
('admin','123','quan ly'),
('tung','123','nhan vien'),
('ly','123','nhan vien')

SELECT Role FROM Users WHERE Username = 'tung' AND Password = '123'

select MaSP,sp.MaLoaiSP,tensp,TenLoaiSP,GiaSP,SoLuong,DanhGia,Hinh from sanpham sp 
inner join LoaiSP lsp on lsp.MaloaiSP = sp.MaLoaiSP

select * from SanPham

  delete from LoaiSP where MaloaiSP = 'LSP006'
  go
  create or alter trigger xoakhoangoaihdct
  on sanpham
  instead of delete 
	as
	begin
		set nocount on
		delete from HoaDonChiTiet where MaSP in (select masp from deleted);
		delete from SanPham where MaSP in (select masp from deleted);
	end
go
	CREATE TRIGGER trgDeleteSanPham
ON SanPham
INSTEAD OF DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Xóa dữ liệu từ bảng SanPham trước
    DELETE FROM SanPham WHERE MaSP IN (SELECT MaSP FROM DELETED);

    -- Xóa dữ liệu từ bảng LoaiSP sau khi đã xóa dữ liệu từ SanPham
    DELETE FROM LoaiSP WHERE MaloaiSP IN (SELECT MaloaiSP FROM DELETED);
END;
go