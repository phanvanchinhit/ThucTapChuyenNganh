/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import bkapt.com.dao.PhieuMuonDAO;
import bkapt.com.dao.SachDAO;
import bkapt.com.dao.SinhVienDAO;
import bkapt.com.dao.TheLoaiSachDAO;
import bkapt.com.dao.ThongKeDAO;
import bkapt.com.model.PhieuMuon;
import bkapt.com.model.Sach;
import bkapt.com.model.SinhVien;
import bkapt.com.model.TheLoaiSach;



/**
 *
 * @author PC
 */
public class ThongKeUI extends javax.swing.JInternalFrame {

    ThongKeDAO tkdao = new ThongKeDAO();
    TheLoaiSachDAO tlsdao = new TheLoaiSachDAO();
    SinhVienDAO svdao = new SinhVienDAO();
    SachDAO sdao = new SachDAO();
    PhieuMuonDAO pmdao = new PhieuMuonDAO();
    ArrayList<TheLoaiSach> listtls;
    ArrayList<SinhVien> listsv;
    ArrayList<Sach> lists;
    ArrayList<PhieuMuon> listpm;
    String key;
    boolean gt;
    
    
    public ThongKeUI() {
        initComponents();
        loadcbbTenTheLoai();
        loadcbbViTriTheLoai();
        loadcbbTenSV();
        loadcbbTenSach();
        loadTableTLS();
        loadTableSV();
        loadTableSach();
        loadTablePM();

        
        
        
        
    }
    public void loadcbbTenTheLoai(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenTLS.getModel();
        listtls = tlsdao.load();
        for(TheLoaiSach tls:listtls){
            model.addElement(tls);
        }
        cboTenTLS.setModel(model);
    }
    public void loadcbbViTriTheLoai(){
        TheLoaiSachDAO tlsdao = new TheLoaiSachDAO();
        ArrayList<String> listcbo = tlsdao.listcbovitri();
        for(String tls : listcbo){
            cboViTriTLS.addItem(tls);
        }
    }
    public void loadcbbTenSV(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenSVPM.getModel();
        listsv = svdao.load();
        for(SinhVien sv:listsv){
            model.addElement(sv);
        }
        cboTenSVPM.setModel(model);
    }
    public void loadcbbTenSach(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSachPM.getModel();
        lists = sdao.load();
        for(Sach s: lists){
            model.addElement(s);
        }
        cboSachPM.setModel(model);
    }
    public void loadTableTLS(){
        DefaultTableModel model = (DefaultTableModel) tblTLS.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKTheLoaiSach();
        for(Object[] row : list){
        model.addRow(row);
        }
        TongTheLoaiTLS();
    }
    public void loadTableMaTLS(){
        DefaultTableModel model = (DefaultTableModel) tblTLS.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKMaTheLoaiSach(key);
        for(Object[] row : list){
        model.addRow(row);
        }
    }
    public void loadTableViTriTLS(){
        DefaultTableModel model = (DefaultTableModel) tblTLS.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKViTriTheLoaiSach(key);
        for(Object[] row : list){
        model.addRow(row);
        }
    }
    public void loadTableGioiTinhSV(){
        DefaultTableModel model = (DefaultTableModel) tblSV.getModel();
        model.setRowCount(0);
        listsv = tkdao.TKGioiTinhSV(gt);
        for(SinhVien sv:listsv){
            Object[] row = new Object[8];
                row[0] = sv.getMaSV();
                row[1] = sv.getPassword();
                row[2] = sv.getHoTen();
                row[3] = sv.getNgaySinh();
                if(sv.isGioiTinh()==true){
                    row[4] = "Nam";
                }else{
                    row[4] = "Nữ";
                }
                row[5] = sv.getDiaChi();
                row[6] = sv.getSdt();
                row[7] = sv.getEmail();
            model.addRow(row);
        } 
    }
    public void loadTableSV(){
        DefaultTableModel model = (DefaultTableModel) tblSV.getModel();
        model.setRowCount(0);
        listsv = svdao.load();
        for(SinhVien sv:listsv){
            Object[] row = new Object[8];
                row[0] = sv.getMaSV();
                row[1] = sv.getPassword();
                row[2] = sv.getHoTen();
                row[3] = sv.getNgaySinh();
                if(sv.isGioiTinh()==false){
                    row[4] = "Nam";
                }else{
                    row[4] = "Nữ";
                }
                row[5] = sv.getDiaChi();
                row[6] = sv.getSdt();
                row[7] = sv.getEmail();
            model.addRow(row);
        }
        TongSV();
    }
    public void loadTableSVChuaMuonSach(){
        DefaultTableModel model = (DefaultTableModel) tblSV.getModel();
        model.setRowCount(0);
        listsv = tkdao.TKSVChuaMuonSach();
        for(SinhVien sv:listsv){
            Object[] row = new Object[8];
                row[0] = sv.getMaSV();
                row[1] = sv.getPassword();
                row[2] = sv.getHoTen();
                row[3] = sv.getNgaySinh();
                if(sv.isGioiTinh()==false){
                    row[4] = "Nam";
                }else{
                    row[4] = "Nữ";
                }
                row[5] = sv.getDiaChi();
                row[6] = sv.getSdt();
                row[7] = sv.getEmail();
            model.addRow(row);
        }
        TongSVChuaMuonSach();
    }
    public void loadTableSVDaMuonSach(){
        DefaultTableModel model = (DefaultTableModel) tblSV.getModel();
        model.setRowCount(0);
        listsv = tkdao.TKSVDaMuonSach();
        for(SinhVien sv:listsv){
            Object[] row = new Object[8];
                row[0] = sv.getMaSV();
                row[1] = sv.getPassword();
                row[2] = sv.getHoTen();
                row[3] = sv.getNgaySinh();
                if(sv.isGioiTinh()==false){
                    row[4] = "Nam";
                }else{
                    row[4] = "Nữ";
                }
                row[5] = sv.getDiaChi();
                row[6] = sv.getSdt();
                row[7] = sv.getEmail();
            model.addRow(row);
        }
        TongSVDaMuonSach();
    }
    public void loadTableSach(){
        SachDAO sdao = new SachDAO();
        lists = sdao.load();
            DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
            model.setRowCount(0);
            for(Sach s: lists){
                Object[] row = new Object[]{
                    s.getMaSach(),s.getTenSach(),s.getMaTheLoai(),s.getTacGia(),s.getSoLuong(),s.getNxb(),s.getNgayNhap(),s.getNdtt(),s.getHinh()
                };
                model.addRow(row);
            }
        TongSach();
    }
    public void loadTableSachGiam(){
        Date date = jDateChooser1.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Date date1 = jDateChooser2.getDate();
        String dff = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKNgaySachGiam(df,dff);
        for(Object[] row : list){
        model.addRow(row);
        }
        SoSachNgaySach();
    }
    public void loadTableSachTang(){
        Date date = jDateChooser1.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Date date1 = jDateChooser2.getDate();
        String dff = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKNgaySachTang(df,dff);
        for(Object[] row : list){
        model.addRow(row);
        }
        SoSachNgaySach();
    }
    public void loadTablePM(){
        DefaultTableModel model = (DefaultTableModel) tblPM.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        TongSVPM();
        TongSachPM();
        TongPM();
    }
    public void loadTableMaSVPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKMaSVPM(key);
        for(Object[] row : list){
        model.addRow(row);
        }
    }
    public void loadTableMaSachPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKMaSachPM(key);
        for(Object[] row : list){
        model.addRow(row);
        }
    }
    public void loadTableSVMuonNhieuSachNhatPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM1.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSVMuonNhieuSachNhatPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        TongSVPM();
        TongSachPM();
    }
    public void loadTableSVMuonItSachNhatPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM1.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSVMuonItSachNhatPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        TongSVPM();
        TongSachPM();
    }
    public void loadTableSachMuonNhieuNhatPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM2.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSachMuonNhieuNhatPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        TongSVPM();
        TongSachPM();
    }
    public void loadTableSachMuonItNhatPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM2.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSachMuonItNhatPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        TongSVPM();
        TongSachPM();
    }
    public void loadTableSVConHanTraSachPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSVConHanTraSachPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        
    }
    public void loadTableSVQuaHanTraSachPM(){
        DefaultTableModel model = (DefaultTableModel) tblPM.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSVQuaHanTraSachPM();
        for(Object[] row : list){
        model.addRow(row);
        }
        
    }
    
    public void SoSachTLS(){
        int tongso = tkdao.TKsoSachTLS(key);
        lblsoSachTLS.setText("Tổng số lượng Sách trong Thể Loại [ "+cboTenTLS.getSelectedItem().toString()+ " ] là: " + String.valueOf(tongso));
    }
    public void SoSachViTriTLS(){
        int tongso = tkdao.TKsoSachViTriTLS(key);
        lblsoSachTLS.setText("Tổng Sách ở Vị trí [ "+cboViTriTLS.getSelectedItem().toString()+ " ] là: " + String.valueOf(tongso));
    }
    public void SoTheLoaiTLS(){
        int tongso = tkdao.TKsoTheLoaiTLS(key);
        lblsoTheLoaiTLS.setText("Tổng số Mã Sách trong Thể Loại [ "+cboTenTLS.getSelectedItem().toString()+ " ] là: " + String.valueOf(tongso));
    }
    public void SoTheLoaiViTriTLS(){
        int tongso = tkdao.TKsoTheLoaiViTriTLS(key);
        lblsoTheLoaiTLS.setText("Tổng Thể Loại ở Vị trí [ "+cboViTriTLS.getSelectedItem().toString()+ " ] là: " + String.valueOf(tongso));
    }
    public void TongTheLoaiTLS(){
        int tongso = tkdao.TKsoTheLoaiTLS();
        lblsoTheLoaiTLS.setText("");
        lblsoSachTLS.setText("Tổng Thể Loại là: " + String.valueOf(tongso));
    }
    public void SoGioiTinhSV(){
        int tongso = tkdao.TKsoGioiTinhSV(gt);
        lblsoGioiTinhSV.setText("Tổng Sinh Viên Giới tính [ "+cboGioiTinhSV.getSelectedItem().toString()+ " ] là: " + String.valueOf(tongso));
    }
    public void TongSV(){
        int tongso = tkdao.TKsoSV();
        lblsoGioiTinhSV.setText("Tổng Sinh Viên là: " + String.valueOf(tongso));
    }
    public void SoSachNgaySach(){
        Date date = jDateChooser1.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Date date1 = jDateChooser2.getDate();
        String dff = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        int tongso = tkdao.TKsoSachNgaySach(df, dff);
        lblTongSachS.setText("Tổng Sách từ ngày [ "+ df + " ] đến ngày [ "+ dff + " ] là: " + String.valueOf(tongso));
    }
    public void TongSach(){
        int tongso = tkdao.TKsoSach();
        lblTongSachS.setText("Tổng Sách là: " + String.valueOf(tongso));
    }
    public void TongSVPM(){
        int tongso = tkdao.TKsoSV();
        lblTongSVPM.setText("Tổng Sinh Viên mượn Sách là: " + String.valueOf(tongso));
    }
    public void TongSachPM(){
        int tongso = tkdao.TKsoSachSVMuon();
        lblTongSachPM.setText("Tổng Sách đã mượn là: " + String.valueOf(tongso));
    }
    public void SoSachSVPM(){
        int tongso = tkdao.TKsoSachSVPM(key);
        lblTongSVPM.setText("Tổng số Sách Sinh Viên [ "+ cboTenSVPM.getSelectedItem().toString() + " ] đã mượn là: " + String.valueOf(tongso));
    }
    public void SoSVMuonSachPM(){
        int tongso = tkdao.TKsoSVMuonSachPM(key);
        lblTongSVPM.setText("Tổng Sinh Viên mượn Sách [ "+cboSachPM.getSelectedItem().toString()+ " ] là: " + String.valueOf(tongso));
    }
    public void TongPM(){
        int tongso = tkdao.TKTongPM();
        lblTongPM.setText("Tổng Phiếu mượn là: " + String.valueOf(tongso));
    }
    public void TongSVChuaMuonSach(){
        int tongso = tkdao.TKTongSVChuaMuonSach();
        lblsoGioiTinhSV.setText("Tổng Sinh Viên chưa mượn Sách là: " + String.valueOf(tongso));
    }
    public void TongSVDaMuonSach(){
        int tongso = tkdao.TKTongSVDaMuonSach();
        lblsoGioiTinhSV.setText("Tổng Sinh Viên đã mượn Sách là: " + String.valueOf(tongso));
    }

    
    




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblsoGioiTinhSV = new javax.swing.JLabel();
        cboGioiTinhSV = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSV = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cboSapXepSV = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTongSachS = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        cboSach = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTLS = new javax.swing.JTable();
        lblsoSachTLS = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTenTLS = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cboViTriTLS = new javax.swing.JComboBox();
        lblsoTheLoaiTLS = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cboTenSVPM = new javax.swing.JComboBox();
        cboSachPM = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboSapXepPM = new javax.swing.JComboBox();
        lblTongPM = new javax.swing.JLabel();
        lblTongSVPM = new javax.swing.JLabel();
        lblTongSachPM = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPM = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPM1 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPM2 = new javax.swing.JTable();

        setBackground(new java.awt.Color(153, 153, 153));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1154, 650));
        setPreferredSize(new java.awt.Dimension(1154, 754));
        setRequestFocusEnabled(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setMaximumSize(new java.awt.Dimension(1600, 754));
        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(1154, 754));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Thống Kê");

        jTabbedPane1.setBackground(new java.awt.Color(197, 197, 197));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1154, 700));

        jPanel1.setBackground(new java.awt.Color(37, 41, 49));

        jPanel5.setBackground(new java.awt.Color(37, 41, 49));
        jPanel5.setMinimumSize(new java.awt.Dimension(1154, 650));
        jPanel5.setPreferredSize(new java.awt.Dimension(1154, 754));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Giới tính: ");

        lblsoGioiTinhSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblsoGioiTinhSV.setForeground(new java.awt.Color(240, 240, 240));
        lblsoGioiTinhSV.setText("Tổng số Sinh Viên: ");

        cboGioiTinhSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboGioiTinhSV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Nam", "Nữ" }));
        cboGioiTinhSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhSVActionPerformed(evt);
            }
        });

        tblSV.setBackground(new java.awt.Color(255, 204, 153));
        tblSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Password", "Họ và tên", "Ngày Sinh", "Giới tính", "Địa chỉ", "Sđt", "Email"
            }
        ));
        tblSV.setRowHeight(30);
        jScrollPane1.setViewportView(tblSV);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Thống kê theo");

        cboSapXepSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboSapXepSV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Sinh Viên Chưa Mượn Sách", "Sinh Viên Đã Mượn Sách" }));
        cboSapXepSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSapXepSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(lblsoGioiTinhSV))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cboGioiTinhSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboSapXepSV, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboSapXepSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboGioiTinhSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblsoGioiTinhSV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sinh Viên", jPanel1);

        jPanel2.setBackground(new java.awt.Color(37, 41, 49));

        jPanel6.setBackground(new java.awt.Color(37, 41, 49));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Từ ngày");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("Đến ngày");

        lblTongSachS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTongSachS.setForeground(new java.awt.Color(240, 240, 240));

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Mã Thể Loại", "Tác Giả", "Số lượng", "NXB", "Ngày nhập", "NDTT", "Hình"
            }
        ));
        tblSach.setRowHeight(30);
        jScrollPane2.setViewportView(tblSach);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Ngày nhập");

        jDateChooser1.setDateFormatString("dd-MM-yyyy");

        jDateChooser2.setDateFormatString("dd-MM-yyyy");

        cboSach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboSach.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Số lượng Sách giảm dần", "Số lượng Sách tăng dần" }));
        cboSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSachActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Sắp xếp theo: ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35)
                        .addComponent(cboSach, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 235, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblTongSachS, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblTongSachS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sách", jPanel2);

        jPanel3.setBackground(new java.awt.Color(37, 41, 49));

        jPanel7.setBackground(new java.awt.Color(37, 41, 49));
        jPanel7.setPreferredSize(new java.awt.Dimension(1000, 412));

        tblTLS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Thể Loại", "Tên Thể Loại", "Vị trí", "Mã Sách", "Tên Sách", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTLS.setRowHeight(30);
        jScrollPane3.setViewportView(tblTLS);
        if (tblTLS.getColumnModel().getColumnCount() > 0) {
            tblTLS.getColumnModel().getColumn(0).setMinWidth(80);
            tblTLS.getColumnModel().getColumn(0).setMaxWidth(80);
            tblTLS.getColumnModel().getColumn(1).setMinWidth(150);
            tblTLS.getColumnModel().getColumn(1).setMaxWidth(150);
            tblTLS.getColumnModel().getColumn(2).setMinWidth(100);
            tblTLS.getColumnModel().getColumn(2).setMaxWidth(100);
            tblTLS.getColumnModel().getColumn(3).setMinWidth(60);
            tblTLS.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        lblsoSachTLS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblsoSachTLS.setForeground(new java.awt.Color(240, 240, 240));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(240, 240, 240));
        jLabel9.setText("Thể loại");

        cboTenTLS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboTenTLS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cboTenTLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenTLSActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(240, 240, 240));
        jLabel10.setText("Vị trí");

        cboViTriTLS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboViTriTLS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cboViTriTLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboViTriTLSActionPerformed(evt);
            }
        });

        lblsoTheLoaiTLS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblsoTheLoaiTLS.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(31, 31, 31)
                                .addComponent(cboTenTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel10))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblsoTheLoaiTLS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addComponent(lblsoSachTLS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(28, 28, 28)
                        .addComponent(cboViTriTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboViTriTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsoSachTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblsoTheLoaiTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thể Loại Sách", jPanel3);

        jPanel9.setBackground(new java.awt.Color(37, 41, 49));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(240, 240, 240));
        jLabel13.setText("Tên SV: ");

        cboTenSVPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboTenSVPM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cboTenSVPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenSVPMActionPerformed(evt);
            }
        });

        cboSachPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboSachPM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cboSachPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSachPMActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(240, 240, 240));
        jLabel14.setText("Sách");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("Thống kê theo");

        cboSapXepPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboSapXepPM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Phiếu Mượn còn hạn trả sách", "Phiếu Mượn quá hạn trả sách", "Sinh Viên mượn nhiều sách nhất", "Sinh Viên mượn ít sách nhất", "Sách mượn nhiều nhất", "Sách mượn ít nhất" }));
        cboSapXepPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSapXepPMActionPerformed(evt);
            }
        });

        lblTongPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTongPM.setForeground(new java.awt.Color(240, 240, 240));
        lblTongPM.setText("Tổng phiếu mượn");

        lblTongSVPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTongSVPM.setForeground(new java.awt.Color(240, 240, 240));
        lblTongSVPM.setText("Tổng SV:");

        lblTongSachPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTongSachPM.setForeground(new java.awt.Color(240, 240, 240));
        lblTongSachPM.setText("Tổng Sách");

        jPanel10.setBackground(new java.awt.Color(197, 197, 197));
        jPanel10.setPreferredSize(new java.awt.Dimension(550, 100));
        jPanel10.setLayout(new java.awt.CardLayout());

        jPanel11.setBackground(new java.awt.Color(197, 197, 197));

        tblPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã PM", "Mã SV", "Tên SV", "Mã Sách", "Tên Sách", "Số lượng", "Ngày Mượn", "Ngày Hẹn Trả"
            }
        ));
        tblPM.setRowHeight(30);
        jScrollPane5.setViewportView(tblPM);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.add(jPanel11, "card2");

        jPanel12.setBackground(new java.awt.Color(197, 197, 197));

        tblPM1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã SV", "Tên SV", "Số lượng"
            }
        ));
        tblPM1.setRowHeight(30);
        jScrollPane6.setViewportView(tblPM1);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel12, "card3");

        jPanel13.setBackground(new java.awt.Color(37, 41, 49));

        tblPM2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Số lượng"
            }
        ));
        tblPM2.setRowHeight(30);
        jScrollPane7.setViewportView(tblPM2);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel13, "card4");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cboTenSVPM, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(cboSachPM, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cboSapXepPM, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblTongSVPM)
                                .addGap(55, 55, 55)
                                .addComponent(lblTongSachPM))
                            .addComponent(lblTongPM))))
                .addContainerGap(453, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(149, 149, 149)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                    .addGap(149, 149, 149)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenSVPM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSachPM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSapXepPM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongSVPM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongSachPM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongPM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Phiếu Mượn", jPanel9);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(jLabel1)
                .addContainerGap(460, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 27, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(671, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(175, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 1130, 724));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSapXepPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSapXepPMActionPerformed
        if(cboSapXepPM.getSelectedItem().equals("Phiếu Mượn còn hạn trả sách")){
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            cboTenSVPM.setSelectedIndex(0);
            cboSachPM.setSelectedIndex(0);
            loadTableSVConHanTraSachPM();
            int TongPM = tkdao.TKTongSoPhieuConHanTraSach();
            int TongSV = tkdao.TKTongSVConHanTraSach();
            lblTongPM.setText(null);
        lblTongSVPM.setText("Tổng Phiếu mượn còn hạn trả sách là: " + String.valueOf(TongPM));
        lblTongSachPM.setText("Tổng Sinh Viên còn hạn trả sách là: " + String.valueOf(TongSV));
        }else if(cboSapXepPM.getSelectedItem().equals("Phiếu Mượn quá hạn trả sách")){
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            cboTenSVPM.setSelectedIndex(0);
            cboSachPM.setSelectedIndex(0);
            loadTableSVQuaHanTraSachPM();
            int TongPM = tkdao.TKTongSoPhieuQuaHanTraSach();
            int TongSV = tkdao.TKTongSVQuaHanTraSach();
            lblTongPM.setText(null);
            lblTongSVPM.setText("Tổng Phiếu mượn quá hạn trả sách là: " + String.valueOf(TongPM));
            lblTongSachPM.setText("Tổng Sinh Viên quá hạn trả sách là: " + String.valueOf(TongSV));
        }else if(cboSapXepPM.getSelectedItem().equals("Sinh Viên mượn nhiều sách nhất")){
            jPanel11.setVisible(false);
            jPanel13.setVisible(false);
            jPanel12.setVisible(true);
            loadTableSVMuonNhieuSachNhatPM();
            lblTongPM.setText(null);
        }else if(cboSapXepPM.getSelectedItem().equals("Sinh Viên mượn ít sách nhất")){
            jPanel11.setVisible(false);
            jPanel13.setVisible(false);
            jPanel12.setVisible(true);
            loadTableSVMuonItSachNhatPM();
            lblTongPM.setText(null);
        }else if(cboSapXepPM.getSelectedItem().equals("Sách mượn nhiều nhất")){
            jPanel11.setVisible(false);
            jPanel12.setVisible(false);
            jPanel13.setVisible(true);
            loadTableSachMuonNhieuNhatPM();
            lblTongPM.setText(null);
        }else if(cboSapXepPM.getSelectedItem().equals("Sách mượn ít nhất")){
            jPanel11.setVisible(false);
            jPanel12.setVisible(false);
            jPanel13.setVisible(true);
            loadTableSachMuonItNhatPM();
            lblTongPM.setText(null);
        }else{
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            loadTablePM();
        }
    }//GEN-LAST:event_cboSapXepPMActionPerformed

    private void cboSachPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSachPMActionPerformed
        if(cboSachPM.getSelectedItem().equals("Tất cả")){
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            loadTablePM();

        }else{
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            cboTenSVPM.setSelectedIndex(0);
            cboSapXepPM.setSelectedIndex(0);
            key = ((Sach)cboSachPM.getSelectedItem()).getMaSach();
            loadTableMaSachPM();
            lblTongPM.setText(null);
            lblTongSachPM.setText(null);
            SoSVMuonSachPM();
        }
    }//GEN-LAST:event_cboSachPMActionPerformed

    private void cboTenSVPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenSVPMActionPerformed
        if(cboTenSVPM.getSelectedItem().equals("Tất cả")){
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            loadTablePM();

        }else{
            jPanel12.setVisible(false);
            jPanel13.setVisible(false);
            jPanel11.setVisible(true);
            cboSachPM.setSelectedIndex(0);
            cboSapXepPM.setSelectedIndex(0);
            key = ((SinhVien)cboTenSVPM.getSelectedItem()).getMaSV();
            loadTableMaSVPM();
            lblTongSachPM.setText(null);
            lblTongPM.setText(null);
            SoSachSVPM();
        }
    }//GEN-LAST:event_cboTenSVPMActionPerformed

    private void cboViTriTLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboViTriTLSActionPerformed
        if(cboViTriTLS.getSelectedItem().equals("Tất cả")){
            loadTableTLS();

        }else{
            cboTenTLS.setSelectedIndex(0);
            key = cboViTriTLS.getSelectedItem().toString();
            loadTableViTriTLS();
            SoSachViTriTLS();
            SoTheLoaiViTriTLS();
        }
    }//GEN-LAST:event_cboViTriTLSActionPerformed

    private void cboTenTLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenTLSActionPerformed
        if(cboTenTLS.getSelectedItem().equals("Tất cả")){
            loadTableTLS();

        }else{
            cboViTriTLS.setSelectedIndex(0);
            key = ((TheLoaiSach)cboTenTLS.getSelectedItem()).getMaTheLoai();
            loadTableMaTLS();
            SoSachTLS();
            SoTheLoaiTLS();
        }
    }//GEN-LAST:event_cboTenTLSActionPerformed

    private void cboSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSachActionPerformed
        if (cboSach.getSelectedItem().equals("Số lượng Sách giảm dần")) {
            loadTableSachGiam();
        }else if(cboSach.getSelectedItem().equals("Số lượng Sách tăng dần")){
            loadTableSachTang();
        }else{
            loadTableSach();
            TongSach();
        }

    }//GEN-LAST:event_cboSachActionPerformed

    private void cboGioiTinhSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhSVActionPerformed
        if(cboGioiTinhSV.getSelectedItem().equals("Tất cả")){
            loadTableSV();
        }else if(cboGioiTinhSV.getSelectedItem().equals("Nữ")){
            cboSapXepSV.setSelectedIndex(0);
            gt = false;
            loadTableGioiTinhSV();
            SoGioiTinhSV();
        }else{
            cboSapXepSV.setSelectedIndex(0);
            gt = true;
            loadTableGioiTinhSV();
            SoGioiTinhSV();
        }

    }//GEN-LAST:event_cboGioiTinhSVActionPerformed

    private void cboSapXepSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSapXepSVActionPerformed
        if(cboSapXepSV.getSelectedItem().equals("Tất cả")){
            loadTableSV();
        }else if(cboSapXepSV.getSelectedItem().equals("Sinh Viên Chưa Mượn Sách")){
            cboGioiTinhSV.setSelectedIndex(0);
            loadTableSVChuaMuonSach();
        }else{
            cboGioiTinhSV.setSelectedIndex(0);
            loadTableSVDaMuonSach();
        }
    }//GEN-LAST:event_cboSapXepSVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboGioiTinhSV;
    private javax.swing.JComboBox cboSach;
    private javax.swing.JComboBox cboSachPM;
    private javax.swing.JComboBox cboSapXepPM;
    private javax.swing.JComboBox cboSapXepSV;
    private javax.swing.JComboBox cboTenSVPM;
    private javax.swing.JComboBox cboTenTLS;
    private javax.swing.JComboBox cboViTriTLS;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTongPM;
    private javax.swing.JLabel lblTongSVPM;
    private javax.swing.JLabel lblTongSachPM;
    private javax.swing.JLabel lblTongSachS;
    private javax.swing.JLabel lblsoGioiTinhSV;
    private javax.swing.JLabel lblsoSachTLS;
    private javax.swing.JLabel lblsoTheLoaiTLS;
    private javax.swing.JTable tblPM;
    private javax.swing.JTable tblPM1;
    private javax.swing.JTable tblPM2;
    private javax.swing.JTable tblSV;
    private javax.swing.JTable tblSach;
    private javax.swing.JTable tblTLS;
    // End of variables declaration//GEN-END:variables
}
