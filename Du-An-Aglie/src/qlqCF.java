
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import java.util.Arrays;
import javax.swing.plaf.DesktopIconUI;

public class qlqCF {

    static ArrayList<SanPham> list = new ArrayList<>();
    private static DefaultTableModel model;
    private static String[] oldHeaders;
    static String checkemail = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    static String pathHinh = "src\\hinh\\";
    static JTable tbsv;
    static int index = 0;
    static String User = "sa";
    static String Pass = "songlong";
    static String Url = "jdbc:sqlserver://localhost:1433;databaseName=QLQUANCAFE;encrypt = false";
    //khai boa frame
    static JFrame fr = new JFrame("Quản Lý Quán CaFe");
    // khai bao label
    static JLabel LbTieuDe = new JLabel("Quản Lý Quán CaFe");
    static JLabel LbMaSP = new JLabel("MaSP:");
    static JLabel LbLoaiSP = new JLabel("LoạiSP:");
    static JLabel LbTenSP = new JLabel("Tên SP");
    static JLabel LbGiaSP = new JLabel("Giá SP");
    static JLabel LbSoLuong = new JLabel("Số lượng:");
    static JLabel LbDanhGia = new JLabel("Danh giá:");
    static JLabel lbHinh = new JLabel("Hình:");
    static JLabel lbTenLSP = new JLabel("Tên loại SP");
    static JLabel LbRecoll = new JLabel();
    // khai bao textField
    static JTextField txtMaSP = new JTextField();
    static JTextField txtLoaiSP = new JTextField();
    static JTextField txtTenSP = new JTextField();
    static JTextField txtGiaSP = new JTextField();
    static JTextField txtSoLuong = new JTextField();
    static JTextField txtTenLoaiSP = new JTextField();
    static JTextArea txtDanhGia = new JTextArea();
    static JScrollPane sp = new JScrollPane(txtDanhGia);
    //khai bao button 
    static JButton btnAdd = new JButton();
    static JButton btnDelete = new JButton();
    static JButton btnUpdate = new JButton();
    static JButton btnSave = new JButton();
    static JButton btnFirst = new JButton();
    static JButton btnPre = new JButton();
    static JButton btnNext = new JButton();
    static JButton btnLast = new JButton();
    static JButton btnReport = new JButton();
    static JButton btnFillReport = new JButton();
    static JButton btnLogOut = new JButton();

    static Border border = BorderFactory.createCompoundBorder(
            new SoftBevelBorder(SoftBevelBorder.RAISED, Color.LIGHT_GRAY, Color.LIGHT_GRAY),
            new EmptyBorder(10, 10, 10, 10));

    static JPanel pnAvatar = new JPanel();
    static JLabel LBHinh = new JLabel();

    static JComboBox<String> cbTenLoaiSP = new JComboBox<>();

    static void Table() {
        // Khai báo model nếu chưa tồn tại
        if (model == null) {
            oldHeaders = new String[]{"Mã SP", "Loại SP", "Tên SP", "Tên loại SP", "Giá SP", "Số lượng", "Đánh giá", "Hinh",};
            model = new DefaultTableModel(oldHeaders, 0);
            tbsv = new JTable(model);
            tbsv.setDefaultEditor(Object.class, null);
            JScrollPane sp = new JScrollPane(tbsv);
            sp.setBounds(10, 410, 760, 150);
            fr.add(sp);
        }
        TaiDuLieuLenArrayList();
        updateTable();
    }

    static String laythongtinbanghi() {
        return "Record " + (index + 1) + " of " + list.size();
    }

    static void updateTable() {
        DefaultTableModel model = (DefaultTableModel) tbsv.getModel();
        model.setRowCount(0);  // Xóa các dòng hiện tại

        for (SanPham sp : list) {
            String fileName = new File(sp.getHinh()).getAbsolutePath();
            Object[] row = {
                sp.getMaSP(),
                sp.getMaLoaiSP(),
                sp.getTenSp(),
                sp.getTenLoaiSp(),
                sp.getGiaSP(),
                sp.getSoLuong(),
                sp.getDanhGia(),
                fileName,};
            model.addRow(row);
        }
    }

    static void Frame() {
        fr.setSize(800, 670);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        fr.setLayout(null);
    }

    static void Label() {
        //tieu de
        LbTieuDe.setForeground(Color.blue);
        LbTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
        LbTieuDe.setBounds(260, 20, 350, 40);

        // noi dung
        LbMaSP.setBounds(40, 80, 70, 20);
        LbMaSP.setFont(new Font("Arial", Font.BOLD, 15));
        LbLoaiSP.setBounds(40, 120, 70, 20);
        LbLoaiSP.setFont(new Font("Arial", Font.BOLD, 15));
        LbTenSP.setBounds(40, 160, 70, 20);
        LbTenSP.setFont(new Font("Arial", Font.BOLD, 15));
        lbTenLSP.setBounds(40, 200, 90, 20);
        lbTenLSP.setFont(new Font("Arial", Font.BOLD, 15));
        LbGiaSP.setBounds(40, 240, 70, 20);
        LbGiaSP.setFont(new Font("Arial", Font.BOLD, 15));
        LbSoLuong.setBounds(40, 280, 90, 20);
        LbSoLuong.setFont(new Font("Arial", Font.BOLD, 15));
        LbDanhGia.setBounds(40, 320, 80, 20);
        LbDanhGia.setFont(new Font("Arial", Font.BOLD, 15));
        LbRecoll.setBounds(90, 340, 190, 40);
        LbRecoll.setFont(new Font("Arial", 1, 25));
        LbRecoll.setForeground(Color.red);

        pnAvatar.add(LBHinh);
        pnAvatar.setBorder(border);
        pnAvatar.setBounds(500, 80, 240, 180);
    }

    static void TextField() {
        txtMaSP.setBounds(130, 80, 350, 20);
        txtLoaiSP.setBounds(130, 120, 350, 20);
        txtTenSP.setBounds(130, 160, 350, 20);
        txtTenLoaiSP.setBounds(130, 200, 350, 20);
        txtGiaSP.setBounds(130, 240, 350, 20);
        txtSoLuong.setBounds(130, 280, 350, 20);
        sp.setBounds(130, 320, 350, 20);

    }

    static void Button() {
        btnAdd.setBounds(500, 280, 50, 40);
        ImageIcon IconAdd = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\add-icon.png");
        Image SizeAdd = IconAdd.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconAdd = new ImageIcon(SizeAdd);
        btnAdd.setIcon(resizedIconAdd);
        btnDelete.setBounds(680, 280, 50, 40);
        ImageIcon IconDelete = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\delete-icon.png");
        Image SizeDelete = IconDelete.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconDelete = new ImageIcon(SizeDelete);
        btnDelete.setIcon(resizedIconDelete);
        btnUpdate.setBounds(620, 280, 50, 40);
        ImageIcon IconUpdate = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\edit-icon.png");
        Image SizeUpdate = IconUpdate.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconUpdate = new ImageIcon(SizeUpdate);
        btnUpdate.setIcon(resizedIconUpdate);
        btnSave.setBounds(560, 280, 50, 40);
        ImageIcon IconSave = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\content-save-edit-icon.png");
        Image SizeSave = IconSave.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSave = new ImageIcon(SizeSave);
        btnSave.setIcon(resizedIconSave);

        btnFirst.setBounds(300, 590, 40, 40);
        ImageIcon iconFirst = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Media-Controls-First-icon.png");
        Image SizeFirst = iconFirst.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconFirst = new ImageIcon(SizeFirst);
        btnFirst.setIcon(resizedIconFirst);

        btnPre.setBounds(360, 590, 40, 40);
        ImageIcon iconPre = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\chevron-left-box-icon.png");
        Image SizePre = iconPre.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconPre = new ImageIcon(SizePre);
        btnPre.setIcon(resizedIconPre);

        btnNext.setBounds(420, 590, 40, 40);
        ImageIcon iconNext = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\chevron-right-box-icon.png");
        Image SizeNext = iconNext.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconNext = new ImageIcon(SizeNext);
        btnNext.setIcon(resizedIconNext);

        btnLast.setBounds(480, 590, 40, 40);
        ImageIcon iconLast = new ImageIcon("D:\\FPT\\java3\\lab5\\icon\\Media-Controls-Last-icon.png");
        Image SizeLast = iconLast.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconLast = new ImageIcon(SizeLast);
        btnLast.setIcon(resizedIconLast);

        btnLogOut.setBounds(500, 330, 50, 40);
        ImageIcon iconLogOut = new ImageIcon("D:\\FPT\\java3\\ASM-Java3\\src\\ASM\\Icon\\Exit.png");
        Image SizeLogOut = iconLogOut.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconLogOut = new ImageIcon(SizeLogOut);
        btnLogOut.setIcon(resizedIconLogOut);
        btnFillReport.setBounds(560, 330, 50, 40);
        ImageIcon iconFillReport = new ImageIcon("D:\\FPT\\java3\\ASM-Java3\\src\\ASM\\Icon\\List.png");
        Image SizeFillReport = iconFillReport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconFillReport = new ImageIcon(SizeFillReport);
        btnFillReport.setIcon(resizedIconFillReport);

        cbTenLoaiSP.setBounds(330, 350, 150, 30);

    }

    static void addCtn() {
        // add label
        fr.add(LbTieuDe);
        fr.add(LbMaSP);
        fr.add(LbLoaiSP);
        fr.add(LbTenSP);
        fr.add(lbTenLSP);
        fr.add(LbGiaSP);
        fr.add(LbSoLuong);
        fr.add(LbDanhGia);

        fr.add(LbRecoll);
        // add textfeild
        fr.add(txtMaSP);
        fr.add(txtLoaiSP);
        fr.add(txtTenSP);
        fr.add(txtTenLoaiSP);
        fr.add(txtGiaSP);
        fr.add(txtSoLuong);
        fr.add(sp);

        fr.add(pnAvatar);

// ADD Button 
        fr.add(btnAdd);
        fr.add(btnDelete);
        fr.add(btnUpdate);
        fr.add(btnSave);
        fr.add(btnFirst);
        fr.add(btnPre);
        fr.add(btnNext);
        fr.add(btnLast);
        fr.add(btnReport);
        fr.add(btnFillReport);
        fr.add(btnLogOut);

        fr.add(cbTenLoaiSP);
    }

    static void updateInFo() {
        int index = tbsv.getSelectedRow();
        if (index >= 0 && index < tbsv.getRowCount()) {
            tbsv.setRowSelectionInterval(index, index);
        }

        int rowCount = tbsv.getRowCount();
        int selectedRow = tbsv.getSelectedRow();
        int recordIndex = (selectedRow == -1) ? 0 : selectedRow + 1;

        LbRecoll.setText("Record " + recordIndex + " of " + rowCount);

    }

    static void chonHinh() {
        try {
            JFileChooser fileChooser = new JFileChooser("D:\\FPT\\java3\\Du-An-Aglie\\src\\hinh");
            fileChooser.setDialogTitle("Chọn hình");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                if (selectedFile.exists()) {
                    String imagePath = selectedFile.getAbsolutePath();
                    list.get(index).setHinh(imagePath);
                    // Hiển thị hình ảnh trong giao diện
                    Uphinh(imagePath);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "File không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void XuLiSuKien() {
        pnAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonHinh();

            }
        });
        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                First();
                index = tbsv.getSelectedRow();
                if (index >= 0 && index < list.size()) {
                    tbsv.setRowSelectionInterval(index, index);
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Last();
                index = tbsv.getSelectedRow();

                if (index >= 0 && index < list.size()) {
                    tbsv.setRowSelectionInterval(index, index);
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
                index = tbsv.getSelectedRow();

                if (index >= 0 && index < list.size()) {
                    tbsv.setRowSelectionInterval(index, index);
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pre();

                index = tbsv.getSelectedRow();
                if (index >= 0 && index < list.size()) {
                    tbsv.setRowSelectionInterval(index, index);
                    updateInFo();
                    HienThiDSlenFrame(index);
                }
            }
        });
        tbsv.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbsv.getSelectedRow();

                    if (selectedRow >= 0 && selectedRow < list.size()) {
                        index = selectedRow;

                        HienThiDSlenFrame(index);
                    }
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSV();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkvalidate()) {
                    UpdateSV();
                    updateInFo();
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkvalidate()) {
                    SaveLSP();
                    SaveSV();
                    updateInFo();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int OK = JOptionPane.showConfirmDialog(fr, "Bạn chắc chắn xoa?", "Delete", JOptionPane.YES_NO_OPTION);
                if (OK == JOptionPane.YES_OPTION) {
                    DeleteSV();
                    updateInFo();
                }
            }
        });

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                fr.dispose();
                login.showLoginFrame();
                if (!login.rdoGhiNho.isSelected()) {
                    login.txtTenDangNhap.setText("");
                    Login.txtMatKhau.setText("");
                }
            }
        });
        btnFillReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Table();
                updateInFo();
                updateTable();
            }
        });
        cbTenLoaiSP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedType = (String) cbTenLoaiSP.getSelectedItem();
                filterTableByType(selectedType);
                updateInFo();
                HienThiDSlenFrame(index);
            }
        });

    }

    static void filterTableByType(String selectedType) {
        DefaultTableModel model = (DefaultTableModel) tbsv.getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

        for (SanPham sp : list) {
            if (sp.getTenLoaiSp().equals(selectedType)) {
                String fileName = new File(sp.getHinh()).getAbsolutePath();
                Object[] row = {
                    sp.getMaSP(),
                    sp.getMaLoaiSP(),
                    sp.getTenSp(),
                    sp.getTenLoaiSp(),
                    sp.getGiaSP(),
                    sp.getSoLuong(),
                    sp.getDanhGia(),
                    fileName
                };
                model.addRow(row);
            }
        }
        updateInFo();
    }

    static boolean checkvalidate() {
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Mã sản phẩm không được bỏ trống");
            return false;
        }
        if (txtLoaiSP.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Loại sản phẩm không được bỏ trống");
            return false;
        }
        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Tên sản phẩm không được bỏ trống");
            return false;
        }
        if (txtTenLoaiSP.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Tên loại sản phẩm không được bỏ trống");
            return false;
        }
        if (txtGiaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Gía sản phẩm không được bỏ trống");
            return false;
        }
        try {
            double giaSP = Double.parseDouble(txtGiaSP.getText());
            if (giaSP <= 0) {
                JOptionPane.showMessageDialog(fr, "Giá sản phẩm không được <=0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(fr, "Giá sản phẩm không hợp lệ");
            return false;
        }

        if (txtSoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Số lượng không được bổ trống");
            return false;
        }
        try {
            int SLSP = Integer.parseInt(txtSoLuong.getText());
            if (SLSP <= 0) {
                JOptionPane.showMessageDialog(fr, "Số lượng không được <=0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(fr, "Giá sản phẩm không hợp lệ");
            return false;
        }

        return true;
    }

    static void HienThiDSlenFrame(int i) {

        if (i >= 0 && i < tbsv.getRowCount()) {
            String MaSP = tbsv.getValueAt(i, 0).toString();
            String MaLSP = tbsv.getValueAt(i, 1).toString();
            String TenSP = tbsv.getValueAt(i, 2).toString();
            String TenLoaiSP = tbsv.getValueAt(i, 3).toString();
            Double GiaSP = Double.parseDouble(tbsv.getValueAt(i, 4).toString());
            int SoLuong = Integer.parseInt(tbsv.getValueAt(i, 5).toString());
            String DanhGia = tbsv.getValueAt(i, 6).toString();
            String hinh = tbsv.getValueAt(i, 7).toString();

            txtMaSP.setText(MaSP);
            txtLoaiSP.setText(MaLSP);
            txtTenSP.setText(TenSP);
            txtTenLoaiSP.setText(TenLoaiSP);
            txtGiaSP.setText(GiaSP + "");
            txtSoLuong.setText(SoLuong + "");
            txtDanhGia.setText(DanhGia);
            Uphinh(hinh);
            updateInFo();
            tbsv.setRowSelectionInterval(i, i);
            Rectangle rect = tbsv.getCellRect(index, 0, true);
            tbsv.scrollRectToVisible(rect);
        }
    }

    static void Uphinh(String hinh) {
        int width = 150; // Thay đổi kích thước mong muốn
        int height = 200;

        File file = new File(hinh);

        if (file.exists()) {
            ImageIcon img = new ImageIcon(hinh);
            Image im = img.getImage();
            ImageIcon icon = new ImageIcon(im.getScaledInstance(width, height, Image.SCALE_SMOOTH));
            LBHinh.setIcon(icon);

        } else {
            System.out.println("File không tồn tại: " + hinh);
        }
    }

    static void LoadDataToCBB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);
            Statement st = con.createStatement();
            String sql = "select TenLoaiSP from LoaiSP";
            ResultSet rs = st.executeQuery(sql);
            cbTenLoaiSP.removeAllItems();
            while (rs.next()) {
                cbTenLoaiSP.addItem(rs.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void TaiDuLieuLenArrayList() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            Statement stm = con.createStatement();
            String sql = "select MaSP,sp.MaLoaiSP,tensp,TenLoaiSP,GiaSP,SoLuong,DanhGia,Hinh from sanpham sp \n"
                    + "inner join LoaiSP lsp on lsp.MaloaiSP = sp.MaLoaiSP";

            ResultSet rs = stm.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String MaSP = rs.getString(1);
                String LoaiSP = rs.getString(2);
                String TenSP = rs.getString(3);
                String TenLoaiSP = rs.getString(4);
                double GiaSp = Double.parseDouble(rs.getString(5));
                int SoLuong = Integer.parseInt(rs.getString(6));
                String DanhGia = rs.getString(7);
                String hinh = rs.getString(8);
                SanPham sp = new SanPham(MaSP, LoaiSP, TenSP, TenLoaiSP, GiaSp, SoLuong, DanhGia, hinh);

                list.add(sp);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void First() {
        if (tbsv.getRowCount() > 0) {
            tbsv.setRowSelectionInterval(0, 0);
        }
    }

    static void Last() {
        int lastRow = tbsv.getRowCount() - 1;
        if (lastRow >= 0) {
            tbsv.setRowSelectionInterval(lastRow, lastRow);
        }
    }

    static void next() {
        int selectedRow = tbsv.getSelectedRow();
        if (selectedRow < tbsv.getRowCount() - 1 && selectedRow >= 0) {
            int nextRow = selectedRow + 1;
            tbsv.setRowSelectionInterval(nextRow, nextRow);
        }
    }

    static void pre() {
        int selectedRow = tbsv.getSelectedRow();
        if (selectedRow > 0) {
            int previousRow = selectedRow - 1;
            tbsv.setRowSelectionInterval(previousRow, previousRow);
        }
    }

    static void addSV() {
        txtMaSP.setText("");
        txtLoaiSP.setText("");
        txtTenSP.setText("");
        txtTenLoaiSP.setText("");
        txtGiaSP.setText("");
        txtSoLuong.setText("");
        txtDanhGia.setText("");
        LBHinh.setIcon(null);
        txtMaSP.requestFocus();
        updateTable();

    }

    static void UpdateSV() {
        // Kiểm tra xem MaSP đã tồn tại trong cơ sở dữ liệu chưa
        if (!CheckTonTaiMaSP(txtMaSP.getText())) {
            JOptionPane.showMessageDialog(fr, "Mã sản phẩm không tồn tại trong cơ sở dữ liệu.");
            return;
        }

        // Kiểm tra xem LoaiSP đã tồn tại trong cơ sở dữ liệu chưa
        if (!CheckTonTaiLoaiSP(txtLoaiSP.getText())) {
            JOptionPane.showMessageDialog(fr, "Loại sản phẩm không tồn tại trong cơ sở dữ liệu.");
            return;
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sqluplsp = "update LoaiSP set TenLoaiSP = ? where MaloaiSP = ?";
            PreparedStatement uploaisp = con.prepareStatement(sqluplsp);
            uploaisp.setString(1, txtTenLoaiSP.getText());
            uploaisp.setString(2, txtLoaiSP.getText());
            uploaisp.executeUpdate();
            String sql = "update SanPham set TenSP=?, GiaSP=?, SoLuong=?, "
                    + "DanhGia=?, Hinh = ? where MaSP = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtTenSP.getText());
            st.setString(2, txtGiaSP.getText());
            st.setString(3, txtSoLuong.getText());
            st.setString(4, txtDanhGia.getText());
            st.setString(5, list.get(index).getHinh()); // Lưu tên hình
            st.setString(6, txtMaSP.getText());
            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Update Thành Công!");
            con.close();
            TaiDuLieuLenArrayList();
            LoadDataToCBB();
            updateTable();
            updateInFo();
            HienThiDSlenFrame(index);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

// Phương thức kiểm tra MaSP tồn tại trong cơ sở dữ liệu
    static boolean CheckTonTaiMaSP(String maSP) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT MaSP FROM SanPham WHERE MaSP = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maSP);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

// Phương thức kiểm tra LoaiSP tồn tại trong cơ sở dữ liệu
    static boolean CheckTonTaiLoaiSP(String loaiSP) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT MaLoaiSP FROM LoaiSP WHERE MaLoaiSP = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiSP);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    static void DeleteSV() {
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(fr, "Nhập mã Sản phẩm cần xóa");
            txtMaSP.requestFocus();
            return;
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);

            // Xóa sản phẩm từ bảng SanPham
            String deleteSQL = "DELETE FROM SanPham WHERE MaSP = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteSQL);
            deleteStmt.setString(1, txtMaSP.getText());
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(fr, "Xóa sản phẩm thành công!");

                // Kiểm tra xem còn sản phẩm nào sử dụng MaLoaiSP này hay không
                String checkSQL = "SELECT COUNT(*) FROM SanPham WHERE MaLoaiSP = ?";
                PreparedStatement checkStmt = con.prepareStatement(checkSQL);
                checkStmt.setString(1, txtLoaiSP.getText());
                ResultSet resultSet = checkStmt.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count == 0) {
                    // Nếu không còn sản phẩm nào sử dụng MaLoaiSP này, xóa nó khỏi bảng LoaiSP
                    DeleteLSP(txtLoaiSP.getText());
                }
            } else {
                JOptionPane.showMessageDialog(fr, "Không tìm thấy sản phẩm cần xóa!");
            }

            con.close();
            index = 0;
            TaiDuLieuLenArrayList();
            LoadDataToCBB();
            updateTable();
            updateInFo();
            HienThiDSlenFrame(index);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi");
        }
    }

    static void DeleteLSP(String maLoaiSP) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);

            // Kiểm tra xem có sản phẩm nào sử dụng MaLoaiSP hay không
            String checkSQL = "SELECT COUNT(*) FROM SanPham WHERE MaLoaiSP = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setString(1, maLoaiSP);
            ResultSet resultSet = checkStmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                // Nếu không còn sản phẩm nào sử dụng MaLoaiSP này, xóa nó khỏi bảng LoaiSP
                String deleteSQL = "DELETE FROM LoaiSP WHERE MaloaiSP = ?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteSQL);
                deleteStmt.setString(1, maLoaiSP);
                deleteStmt.executeUpdate();
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi khi xóa Loại Sản phẩm");
        }
    }

    static void SaveLSP() {
        if (CheckTrungLSP(txtLoaiSP.getText())) {
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "INSERT INTO LoaiSP (MaloaiSP, TenLoaiSP) VALUES (?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtLoaiSP.getText());
            st.setString(2, txtTenLoaiSP.getText());

            st.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void SaveSV() {
        if (CheckTrung(txtMaSP.getText())) {
            JOptionPane.showMessageDialog(fr, "Mã sản phẩm đã tồn tại trong cơ sở dữ liệu.");
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = (Connection) DriverManager.getConnection(Url, User, Pass);
            String sql = "INSERT INTO SanPham (MaSP, MaLoaiSP, TenSP, GiaSP, SoLuong, DanhGia, Hinh) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtMaSP.getText());
            st.setString(2, txtLoaiSP.getText());
            st.setString(3, txtTenSP.getText());
            st.setString(4, txtGiaSP.getText());
            st.setString(5, txtSoLuong.getText());
            st.setString(6, txtDanhGia.getText());
            st.setString(7, list.get(index).getHinh()); // Đường dẫn hình ảnh

            st.executeUpdate();
            JOptionPane.showMessageDialog(fr, "Thêm sản phẩm thành công!");
            con.close();

            // Sau khi thêm sản phẩm, cập nhật lại danh sách sản phẩm
            TaiDuLieuLenArrayList();
            LoadDataToCBB();
            updateTable();
            updateInFo();
            HienThiDSlenFrame(index);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, "Lỗi khi thêm sản phẩm");
        }
    }

    static boolean CheckTrung(String MaSP) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT MaSP FROM SanPham WHERE MaSP = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, MaSP);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    static boolean CheckTrungLSP(String MaSP) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(Url, User, Pass);

            String sql = "SELECT MaLoaiSP FROM LoaiSP WHERE MaLoaiSP = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, MaSP);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public static void main() {
        Frame();
        addCtn();
        Label();
        TextField();

        Button();
        Table();
        updateInFo();
        LoadDataToCBB();
        TaiDuLieuLenArrayList();
        HienThiDSlenFrame(index);
        XuLiSuKien();
        fr.setVisible(true);
    }
}
