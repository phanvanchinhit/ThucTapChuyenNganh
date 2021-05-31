/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.ui;

import bkapt.com.dao.LopDAO;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import bkapt.com.dao.SinhVienDAO;
import bkapt.com.model.Lop;
import bkapt.com.model.SinhVien;
import bkapt.com.model.TheLoaiSach;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author PC
 */
public class QuanLySinhVien extends javax.swing.JInternalFrame {
    ArrayList<SinhVien> listsv;
    ArrayList<Lop> listlop;
    DefaultComboBoxModel modelLop;
    int current;
    public QuanLySinhVien() {
        initComponents();
        load();
        loadLop();
        setStatus(true);
    }
    public void load(){
        SinhVienDAO svdao = new SinhVienDAO();
        listsv = svdao.load();
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        for(SinhVien sv:listsv){
            Object[] row = new Object[9];
                row[0] = sv.getMaSV();
                row[1] = sv.getPassword();
                row[2] = sv.getMalop();
                row[3] = sv.getHoTen();
                row[4] = sv.getNgaySinh();
                if(sv.isGioiTinh()==true){
                    row[5] = "Nam";
                }else if(sv.isGioiTinh()==false){
                    row[5] = "Nữ";
                }
                row[6] = sv.getDiaChi();
                row[7] = sv.getSdt();
                row[8] = sv.getEmail();
            model.addRow(row);
        }      
    }
    public void loadLop(){
        LopDAO lpdao = new LopDAO();
        modelLop = (DefaultComboBoxModel) cblop.getModel();
        listlop = lpdao.load();
        for(Lop lp:listlop){
            modelLop.addElement(lp);
        }
        cblop.setModel(modelLop);
    }
    public void insert(){
        modelLop = (DefaultComboBoxModel) cblop.getModel();
        SinhVien sv =  new SinhVien();
        Lop lop = (Lop) cblop.getSelectedItem();
        sv.setMaSV(txtMaSV.getText());
        sv.setPassword(txtPassword.getText());
        sv.setHoTen(txtHoTen.getText());
        sv.setMalop(lop.getMalop());
        boolean gt;
        if(rdoNam.isSelected())
            gt=true;
        else 
            gt=false;
        sv.setGioiTinh(gt);
        sv.setDiaChi(txtDiaChi.getText());
        sv.setSdt(txtSDT.getText());
        sv.setMalop(lop.getMalop());
        sv.setEmail(txtEmail.getText());
        Date date = jDateChooser.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        sv.setNgaySinh(df);
        SinhVienDAO svdao =new SinhVienDAO() ;
        if (svdao.insert(sv)>0 )
        {    
            JOptionPane.showMessageDialog(null, "Thêm Sinh Viên thành công");
        }
        else{
            JOptionPane.showMessageDialog(null, "Mã Sinh Viên [ "+txtMaSV.getText()+" ] đã tồn tại không thể thêm");
        }     
    }
    public void update(){
        modelLop = (DefaultComboBoxModel) cblop.getModel();
        Lop lop = (Lop) cblop.getSelectedItem();
        SinhVien sv =  new SinhVien();
        sv.setMaSV(txtMaSV.getText());
        sv.setPassword(txtPassword.getText());
        sv.setHoTen(txtHoTen.getText());
        boolean gt;
        if(rdoNam.isSelected())
            gt=true;
        else 
            gt=false;
        sv.setGioiTinh(gt);
        sv.setDiaChi(txtDiaChi.getText());
        sv.setSdt(txtSDT.getText());
        sv.setMalop(lop.getMalop());
        sv.setEmail(txtEmail.getText());
        Date date = jDateChooser.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        sv.setNgaySinh(df);
        SinhVienDAO svdao =new SinhVienDAO() ;
        if (svdao.update(sv)>0 )
        {    
            JOptionPane.showMessageDialog(null, "cập nhật thành công");
        }
        else{
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }     
    }
    public void delete(){
        int index = tblBang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Sinh Viên trong bảng để xóa", "Thông Báo", 1);
            return;
        }
        SinhVienDAO svdao =new SinhVienDAO() ;
        
        int tk = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
      if (tk==JOptionPane.YES_OPTION)  
      {
        if (svdao.delete(txtMaSV.getText()))
        {
            JOptionPane.showMessageDialog(this, "Xóa Sinh Viên thành công", "Thông Báo", 1);
            
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống", "Thông Báo", 0);
        }
        
      }
      else
      {
          return;
          
      }
    }
    
    
    public void loadTen(){
        SinhVienDAO svdao =new SinhVienDAO() ;
        listsv = svdao.SearchTen(txtSearch.getText());
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            model.setRowCount(0);
            for(SinhVien sv: listsv){
                Object[] row = new Object[]{
                    sv.getMaSV(),
                    sv.getPassword(),
                    sv.getHoTen(),
                    sv.getNgaySinh(),
                    sv.isGioiTinh(),
                    sv.getDiaChi(),
                    sv.getSdt(),
                    sv.getEmail()
                };
                model.addRow(row);
            }
    }
    public void clear(){
        txtMaSV.setText(null);
        txtPassword.setText(null);
        txtHoTen.setText(null);
        rdoNam.setEnabled(true);
        txtDiaChi.setText(null);
        txtSDT.setText(null);
        txtEmail.setText(null);
        lblMaSV1.setText("");
        lblDiaChi1.setText("");
        lblEmail1.setText("");
        lblHoTen1.setText("");
        lblPass1.setText("");
        lblSDT1.setText("");
        buttonGroup1.clearSelection();
        setStatus(true);
    }
    public void display(){
        SinhVien sv = listsv.get(current);
        txtMaSV.setText(sv.getMaSV());
        txtPassword.setText(sv.getPassword());
        txtHoTen.setText(sv.getHoTen());
        try {
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(current, 4));
            jDateChooser.setDate(date);
            } catch (ParseException ex) {   
        }
        if(sv.isGioiTinh()==true){
            rdoNam.setSelected(true);
        }
        if(sv.isGioiTinh()==false){
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(sv.getDiaChi());
        txtSDT.setText(sv.getSdt());
        txtEmail.setText(sv.getEmail());
    }
    public void setStatus(boolean insertable){
    txtMaSV.setEditable(insertable);
    btnThem.setEnabled(insertable);
    btnSua.setEnabled(!insertable);
    btnXoa.setEnabled(!insertable);
    }
    public void indssv() throws JRException{
        SinhVienDAO svdao = new SinhVienDAO();
        svdao.indssv();
    }
    public boolean valiform() {
        if (txtMaSV.getText().equals("")) {
            txtMaSV.requestFocus();
            lblMaSV1.setText("Chưa nhập Mã Sinh Viên");
            return false;
        }else if (txtPassword.getText().equals("")) {
            txtPassword.requestFocus();
            lblPass1.setText("Chưa nhập Password");
            return false;
        }else if (!(txtPassword.getText()).matches("\\w{3,50}")) {
            txtPassword.requestFocus();
            lblPass1.setText("Mật khẩu ít nhất 3 kí tự");
            return false;
        }else if (txtHoTen.getText().equals("")) {
            txtHoTen.requestFocus();
            lblHoTen1.setText("Chưa nhập Họ Tên");
            return false;
        }else if (!(txtHoTen.getText().matches("\\D*"))) {
            txtHoTen.requestFocus();
            lblHoTen1.setText("Họ Tên phải là chữ");
            return false;
        }else if (buttonGroup1.isSelected(null)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Giới Tính");
            return false;
        }else if (txtDiaChi.getText().equals("")) {
            txtDiaChi.requestFocus();
            lblDiaChi1.setText("Chưa nhập Địa chỉ");
            return false;
        }else if (txtSDT.getText().equals("")) {
            lblSDT1.setText("Chưa nhập SĐT");
            txtSDT.requestFocus();     
            return false;
        }else if (!(txtSDT.getText().matches("\\d{10,11}"))) {
            txtSDT.requestFocus();
            lblSDT1.setText("SĐT phải là số, 10 - 11 số");
            return false;
        }else if (txtEmail.getText().equals("")) {
            txtEmail.requestFocus(); 
            lblEmail1.setText("Chưa nhập Email");
            return false;
        }else if (!(txtEmail.getText().matches("\\w+@\\w+\\.\\w{1,3}"))) {
            txtEmail.requestFocus(); 
            lblEmail1.setText("Nhập Email đúng định dạng");
            return false;
        }else 
        return true;
    };


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlForm = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        pnlThongTin = new javax.swing.JPanel();
        lblMaSV = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        lblMaSV1 = new javax.swing.JLabel();
        lblPass1 = new javax.swing.JLabel();
        lblHoTen1 = new javax.swing.JLabel();
        lblDiaChi1 = new javax.swing.JLabel();
        lblSDT1 = new javax.swing.JLabel();
        lblEmail1 = new javax.swing.JLabel();
        lblGioiTinh1 = new javax.swing.JLabel();
        cblop = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnTaoMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        pnl2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setForeground(java.awt.Color.darkGray);
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMinimumSize(new java.awt.Dimension(1200, 650));
        setPreferredSize(new java.awt.Dimension(1154, 754));

        pnlForm.setBackground(new java.awt.Color(153, 153, 153));
        pnlForm.setMinimumSize(new java.awt.Dimension(1200, 650));
        pnlForm.setPreferredSize(new java.awt.Dimension(1154, 754));

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Quản Lý Sinh Viên");

        tblBang.setBackground(new java.awt.Color(255, 204, 102));
        tblBang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblBang.setForeground(new java.awt.Color(240, 240, 240));
        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Masv", "Password", "Lớp", "Họ và tên", "Ngày Sinh", "Giới tính", "Địa chỉ", "Sđt", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang.setRowHeight(30);
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBang);
        if (tblBang.getColumnModel().getColumnCount() > 0) {
            tblBang.getColumnModel().getColumn(0).setMinWidth(60);
            tblBang.getColumnModel().getColumn(0).setMaxWidth(60);
            tblBang.getColumnModel().getColumn(1).setMinWidth(68);
            tblBang.getColumnModel().getColumn(1).setMaxWidth(68);
            tblBang.getColumnModel().getColumn(2).setMinWidth(100);
            tblBang.getColumnModel().getColumn(3).setMinWidth(80);
            tblBang.getColumnModel().getColumn(4).setMinWidth(60);
            tblBang.getColumnModel().getColumn(4).setMaxWidth(60);
            tblBang.getColumnModel().getColumn(5).setMinWidth(65);
            tblBang.getColumnModel().getColumn(5).setMaxWidth(65);
            tblBang.getColumnModel().getColumn(6).setMinWidth(80);
            tblBang.getColumnModel().getColumn(6).setMaxWidth(80);
            tblBang.getColumnModel().getColumn(7).setMinWidth(100);
        }

        pnlThongTin.setBackground(new java.awt.Color(209, 202, 202));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        pnlThongTin.setPreferredSize(new java.awt.Dimension(715, 262));

        lblMaSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMaSV.setText("Mã SV:");

        lblPassword.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPassword.setText("Password");

        lblHoTen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHoTen.setText("Họ Và tên:");

        lblNgaySinh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNgaySinh.setText("Ngày Sinh");

        lblGioiTinh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGioiTinh.setText("Giới Tính:");

        lblDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDiaChi.setText("Địa Chỉ:");

        lblSDT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSDT.setText("SĐT: ");

        lblEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblEmail.setText("Emai:");

        txtMaSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSVKeyReleased(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        txtHoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoTenKeyReleased(evt);
            }
        });

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        txtDiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiaChiKeyReleased(evt);
            }
        });

        rdoNam.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoNu.setText("Nữ");

        jDateChooser.setDateFormatString("dd-MM-yyyy");
        jDateChooser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblMaSV1.setForeground(new java.awt.Color(255, 0, 0));

        lblPass1.setForeground(new java.awt.Color(255, 0, 0));

        lblHoTen1.setForeground(new java.awt.Color(255, 0, 0));

        lblDiaChi1.setForeground(new java.awt.Color(255, 0, 0));

        lblSDT1.setForeground(new java.awt.Color(255, 0, 0));

        lblEmail1.setForeground(new java.awt.Color(255, 0, 0));

        lblGioiTinh1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGioiTinh1.setText("Lớp :");

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDiaChi)
                                    .addComponent(lblSDT)
                                    .addComponent(lblEmail))
                                .addGap(23, 23, 23)
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                        .addComponent(lblSDT1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSDT)
                                        .addComponent(lblDiaChi1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi))))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(lblGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cblop, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblGioiTinh))
                            .addComponent(lblMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoTen1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaSV, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPassword)
                                    .addComponent(lblPass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblMaSV1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblMaSV1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoTen)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lblHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgaySinh)
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh1)
                    .addComponent(cblop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblSDT))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Save.png"))); // NOI18N
        btnThem.setText("Lưu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnTaoMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTaoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Create.png"))); // NOI18N
        btnTaoMoi.setText("Tạo Mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Save as.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        pnl2.setBackground(new java.awt.Color(209, 202, 202));
        pnl2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm theo Mã hoặc Tên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Search.png"))); // NOI18N

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch)
                .addContainerGap())
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(txtSearch)
        );

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addComponent(btnSua)
                        .addGap(147, 147, 147)
                        .addComponent(btnXoa))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                    .addComponent(pnl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(411, 411, 411))
        );

        pnlFormLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnTaoMoi, btnThem, btnXoa});

        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addComponent(pnl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        pnlFormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua, btnTaoMoi, btnThem, btnXoa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, 1141, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoMoiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnTaoMoiActionPerformed
    
    private void btnThemActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(valiform()==true){
            insert();
            load();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblBangMouseClicked(MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        current = tblBang.getSelectedRow();
//        System.out.println(current);
        display();
        setStatus(false);
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnXoaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
        clear();
        load();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(valiform()==true){
            update();
            load();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        loadTen();
        
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtMaSVKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtMaSVKeyReleased
        if(!txtMaSV.getText().equals("")){
            lblMaSV1.setText(null);
        }
    }//GEN-LAST:event_txtMaSVKeyReleased

    private void txtPasswordKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        if(!txtPassword.getText().equals("")){
            lblPass1.setText(null);
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtHoTenKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtHoTenKeyReleased
        if(!txtHoTen.getText().equals("")){
            lblHoTen1.setText(null);
        }
    }//GEN-LAST:event_txtHoTenKeyReleased

    private void txtDiaChiKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtDiaChiKeyReleased
        if(!txtDiaChi.getText().equals("")){
            lblDiaChi1.setText(null);
        }
    }//GEN-LAST:event_txtDiaChiKeyReleased

    private void txtSDTKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        if(!txtSDT.getText().equals("")){
            lblSDT1.setText(null);
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtEmailKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if(!txtEmail.getText().equals("")){
            lblEmail1.setText(null);
        }
    }//GEN-LAST:event_txtEmailKeyReleased

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
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cblop;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiaChi1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblGioiTinh1;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblHoTen1;
    private javax.swing.JLabel lblMaSV;
    private javax.swing.JLabel lblMaSV1;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblPass1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSDT1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
