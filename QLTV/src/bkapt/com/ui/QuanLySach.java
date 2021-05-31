/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.ui;

import bkapt.com.dao.NXBDAO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import bkapt.com.dao.SachDAO;
import bkapt.com.dao.TacGiaDAO;
import bkapt.com.dao.TheLoaiSachDAO;
import bkapt.com.helper.ShareHelper;
import bkapt.com.model.NXB;
import bkapt.com.model.Sach;
import bkapt.com.model.TacGia;
import bkapt.com.model.TheLoaiSach;
import java.util.logging.ErrorManager;

/**
 *
 * @author PC
 */
public class QuanLySach extends javax.swing.JInternalFrame {

    ArrayList<Sach> lists;
    ArrayList<TheLoaiSach> listtls;
    ArrayList<TacGia> listtg;
    ArrayList<NXB> listNXB;
    int current;
    JFileChooser fileChooser = new JFileChooser();
    DefaultComboBoxModel modelTheLoai,modelTG,modelNXB;
    public QuanLySach() {
        initComponents();
        load();
        loadcbbTenTheLoai();
        loadcbbTacGia();
        loadcbbNXB();
        setStatus(true);
    }
    public void load(){
        SachDAO sdao = new SachDAO();
        lists = sdao.load();
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            model.setRowCount(0);
            for(Sach s: lists){
                Object[] row = new Object[]{
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getMaTheLoai(),
                    s.getTacGia(),
                    s.getSoLuong(),
                    s.getNxb(),
                    s.getNgayNhap(),
                    s.getNdtt(),
                    s.getHinh()
                };
                model.addRow(row);
            }
           
    }

    public void loadcbbTenTheLoai(){
        TheLoaiSachDAO tlsdao = new TheLoaiSachDAO();
        modelTheLoai = (DefaultComboBoxModel) cboTheLoai.getModel();
        listtls = tlsdao.load();
        for(TheLoaiSach tls:listtls){
            modelTheLoai.addElement(tls);
        }
        cboTheLoai.setModel(modelTheLoai);
    }
     public void loadcbbTacGia(){
        TacGiaDAO tgdao = new TacGiaDAO();
        modelTG = (DefaultComboBoxModel) cboTacGia.getModel();
        listtg = tgdao.load();
        for(TacGia tg:listtg){
            modelTG.addElement(tg);
        }
        cboTacGia.setModel(modelTG);
    }
      public void loadcbbNXB(){
        NXBDAO nxbdao = new NXBDAO();
        modelNXB = (DefaultComboBoxModel) cboNXB.getModel();
        listNXB = nxbdao.load();
        for(NXB nxb:listNXB){
            modelNXB.addElement(nxb);
        }
        cboNXB.setModel(modelNXB);
    }
    public void insert(){
        Sach s =  new Sach();
        TheLoaiSach theloaisach = (TheLoaiSach) cboTheLoai.getSelectedItem();
        TacGia tacgia = (TacGia) cboTacGia.getSelectedItem();
        NXB nxb = (NXB) cboNXB.getSelectedItem();
        s.setMaSach(txtMaSach.getText());
        s.setTenSach(txtTenSach.getText());
        s.setMaTheLoai(theloaisach.getMaTheLoai());
        s.setTacGia(tacgia.getMatg());
        s.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        s.setNxb(nxb.getMaNXB());
        Date date = jDateChooser.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        s.setNgayNhap(df);
        s.setNdtt(txtNoiDung.getText());
        s.setHinh(lblHinh.getToolTipText());
        SachDAO sdao = new SachDAO() ;
        if (sdao.insert(s)>0 )
        {    
            JOptionPane.showMessageDialog(null, "Thêm Sách thành công");
        }
        else{
            JOptionPane.showMessageDialog(null, "Mã Sách [ "+txtMaSach.getText()+" ] đã tồn tại không thể thêm");
        }     
        
    }
        public void update(){
        Sach s =  new Sach();
        TheLoaiSach theloaisach = (TheLoaiSach) cboTheLoai.getSelectedItem();
        TacGia tacgia = (TacGia) cboTacGia.getSelectedItem();
        NXB nxb = (NXB) cboNXB.getSelectedItem();
        s.setMaSach(txtMaSach.getText());
        s.setTenSach(txtTenSach.getText());
        s.setMaTheLoai(theloaisach.getMaTheLoai());
        s.setTacGia(tacgia.getMatg());
        s.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        s.setNxb(nxb.getMaNXB());
        s.setNdtt(txtNoiDung.getText());
        s.setHinh(lblHinh.getToolTipText());
        ImageIcon image = (ImageIcon) lblHinh.getIcon();
        ImageIcon resizedImage = resize(image, 126, 152); 
        lblHinh.setIcon(resizedImage);
        Date date = jDateChooser.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        s.setNgayNhap(df);
        SachDAO sdao =new SachDAO() ;
        if (sdao.update(s)>0 )
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
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Sách trong bảng để xóa", "Thông Báo", 1);
            return;
        }
        SachDAO sdao = new SachDAO();
        
        int tk = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
      if (tk==JOptionPane.YES_OPTION)  
      {
        if (sdao.delete(txtMaSach.getText()))
        {
            JOptionPane.showMessageDialog(this, "Xóa Sách thành công", "Thông Báo", 1);
            
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
        SachDAO sdao = new SachDAO();
        lists = sdao.SearchTen(txtSearch.getText());
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            model.setRowCount(0);
            for(Sach s: lists){
                Object[] row = new Object[]{
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getMaTheLoai(),
                    s.getTacGia(),
                    s.getSoLuong(),
                    s.getNxb(),
                    s.getNgayNhap(),
                    s.getNdtt(),
                    s.getHinh()
                };
                model.addRow(row);
            }
    }

    public void clear(){
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
        txtNoiDung.setText("");
        lblMaSach1.setText("");
        lblNXB1.setText("");
        lblNoiDung1.setText("");
        lblSoLuong1.setText("");
        lblTenSach1.setText("");
        lblTheLoai1.setText("");
        lblHinh.setIcon(null);
        setStatus(true);
    }
    public void display(){
        if (current >=0) {
            Sach s = lists.get(current);
        txtMaSach.setText(s.getMaSach());
        txtTenSach.setText(s.getTenSach());
        txtSoLuong.setText(String.valueOf(s.getSoLuong()));
        try {
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(current, 6));
            jDateChooser.setDate(date);
            } catch (ParseException ex) {   
        }
        txtNoiDung.setText(s.getNdtt());
        lblHinh.setToolTipText(s.getHinh());
        ImageIcon image = new ImageIcon(getClass().getResource("/bkapt/com/image/"+s.getHinh()));
        ImageIcon resizedImage = resize(image, 126, 152); 
        lblHinh.setIcon(resizedImage);
        }
        
    }
        public static ImageIcon resize(ImageIcon imageIcon, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);
    }
        public void indssach() throws JRException{
        SachDAO sdao = new SachDAO();
        sdao.indssach();
    }
    public void setStatus(boolean insertable){
    txtMaSach.setEditable(insertable);
    btnThem.setEnabled(insertable);
    btnSua.setEnabled(!insertable);
    btnXoa.setEnabled(!insertable);
    }
    public boolean valiform() {
        if (txtMaSach.getText().equals("")) {
            txtMaSach.requestFocus();
            lblMaSach1.setText("Chưa nhập Mã Sách");
            return false;
        }else if (txtTenSach.getText().equals("")) {
            txtTenSach.requestFocus();
            lblTenSach1.setText("Chưa nhập Tên Sách");
            return false;
        }else if (lblHinh.getToolTipText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn Hình Sách","Error",1);
            return false;
        }else if (txtSoLuong.getText().equals("")) {
            txtSoLuong.requestFocus();
            lblSoLuong1.setText("Chưa nhập Số lượng");
            return false;
        }else if (!(txtSoLuong.getText().matches("\\d{1,3}"))) {
            txtSoLuong.requestFocus();
            lblSoLuong1.setText("Số lượng phải là số, lớn hơn 0");
            return false;
        }else if ((!(Integer.parseInt(txtSoLuong.getText())> 0))) {
            txtSoLuong.requestFocus();
            lblSoLuong1.setText("Số lượng phải lớn hơn 0");
            return false;
        }else if (jDateChooser.isValid()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn Ngày nhập Sách","Error",1);
            return false;
        }else if (txtNoiDung.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập Nội dung Sách","Error",1);
            txtNoiDung.requestFocus(); 
            lblNoiDung1.setText("Chưa nhập Nội dung");
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
        jPanel3 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnTaoMoi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblMaSach = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        lblNXB = new javax.swing.JLabel();
        lblTacGia = new javax.swing.JLabel();
        lblTheLoai = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblNgayNhap = new javax.swing.JLabel();
        lblNoiDung = new javax.swing.JLabel();
        pnlAnh = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cboTheLoai = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        lblMaSach1 = new javax.swing.JLabel();
        lblTenSach1 = new javax.swing.JLabel();
        lblNXB1 = new javax.swing.JLabel();
        lblTacGia1 = new javax.swing.JLabel();
        lblTheLoai1 = new javax.swing.JLabel();
        lblSoLuong1 = new javax.swing.JLabel();
        lblNoiDung1 = new javax.swing.JLabel();
        cboTacGia = new javax.swing.JComboBox<>();
        cboNXB = new javax.swing.JComboBox<>();
        lblTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        btnXoa1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 650));
        setPreferredSize(new java.awt.Dimension(1154, 754));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setMinimumSize(new java.awt.Dimension(1600, 754));
        jPanel3.setPreferredSize(new java.awt.Dimension(1600, 754));

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

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Save.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnTaoMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTaoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Create.png"))); // NOI18N
        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(209, 202, 202));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        lblMaSach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMaSach.setText("Mã sách:");

        lblTenSach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSach.setText("Tên sách:");

        lblNXB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNXB.setText("NXB:");

        lblTacGia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTacGia.setText("Tác giả: ");

        lblTheLoai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTheLoai.setText("Thể loại:");

        lblSoLuong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSoLuong.setText("Số lượng");

        lblNgayNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNgayNhap.setText("Ngày nhập");

        lblNoiDung.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNoiDung.setText("Nội dung");

        pnlAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlAnh.setMaximumSize(new java.awt.Dimension(150, 184));
        pnlAnh.setMinimumSize(new java.awt.Dimension(150, 184));
        pnlAnh.setPreferredSize(new java.awt.Dimension(150, 184));
        pnlAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlAnhMouseClicked(evt);
            }
        });

        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlAnhLayout = new javax.swing.GroupLayout(pnlAnh);
        pnlAnh.setLayout(pnlAnhLayout);
        pnlAnhLayout.setHorizontalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAnhLayout.setVerticalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtMaSach.setPreferredSize(new java.awt.Dimension(59, 30));
        txtMaSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSachKeyReleased(evt);
            }
        });

        txtTenSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenSachKeyReleased(evt);
            }
        });

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        cboTheLoai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheLoaiActionPerformed(evt);
            }
        });

        txtNoiDung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoiDungKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtNoiDung);

        jDateChooser.setDateFormatString("dd-MM-yyyy");

        lblMaSach1.setForeground(new java.awt.Color(255, 0, 0));

        lblTenSach1.setForeground(new java.awt.Color(255, 0, 0));

        lblNXB1.setForeground(new java.awt.Color(255, 0, 0));

        lblTacGia1.setForeground(new java.awt.Color(255, 0, 0));

        lblTheLoai1.setForeground(new java.awt.Color(255, 0, 0));

        lblSoLuong1.setForeground(new java.awt.Color(255, 0, 0));

        lblNoiDung1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNoiDung)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoiDung1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMaSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
                            .addComponent(lblSoLuong)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMaSach)
                                .addGap(23, 23, 23)
                                .addComponent(txtMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(lblTenSach)
                                            .addGap(8, 8, 8))
                                        .addComponent(lblNXB))
                                    .addComponent(lblTheLoai))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSach)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTheLoai1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cboTheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblTenSach1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNXB1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 40, Short.MAX_VALUE))
                                    .addComponent(cboNXB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pnlAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTacGia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTacGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSach)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSach)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblNXB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 51, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboNXB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(lblNXB1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTacGia)
                        .addComponent(lblTheLoai))
                    .addComponent(cboTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTheLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTheLoai1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuong)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 29, Short.MAX_VALUE)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNoiDung)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoiDung1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Quản Lý Sách");

        jPanel2.setBackground(new java.awt.Color(209, 202, 202));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm theo Mã Sách, Tên Sách hoặc Mã TL ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(340, 56));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblBang.setBackground(new java.awt.Color(255, 204, 153));
        tblBang.setForeground(new java.awt.Color(255, 255, 255));
        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Mã TL", "Tác Giả", "Số Lượng", "NXB", "Ngày Nhập", "Nội Dung", "Hình"
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
        jScrollPane2.setViewportView(tblBang);
        if (tblBang.getColumnModel().getColumnCount() > 0) {
            tblBang.getColumnModel().getColumn(0).setMinWidth(60);
            tblBang.getColumnModel().getColumn(0).setMaxWidth(60);
            tblBang.getColumnModel().getColumn(2).setMinWidth(60);
            tblBang.getColumnModel().getColumn(2).setMaxWidth(60);
            tblBang.getColumnModel().getColumn(4).setMinWidth(60);
            tblBang.getColumnModel().getColumn(4).setMaxWidth(60);
            tblBang.getColumnModel().getColumn(5).setResizable(false);
            tblBang.getColumnModel().getColumn(7).setResizable(false);
        }

        btnXoa1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Delete.png"))); // NOI18N
        btnXoa1.setText("Xóa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(273, 273, 273)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(560, 560, 560)
                        .addComponent(lblTitle)))
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(738, 738, 738)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(738, Short.MAX_VALUE)))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem, btnXoa});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(357, 357, 357)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(357, Short.MAX_VALUE)))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua, btnTaoMoi, btnThem, btnXoa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        current = tblBang.getSelectedRow();
        cboTheLoai.setSelectedIndex(current);
        cboTacGia.setSelectedIndex(current);
        cboNXB.setSelectedIndex(current);
        display();
        setStatus(false);
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(valiform()==true){
            insert();
            load();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(valiform()==true){
            update();
            load();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
        clear();
        load();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
         try {
            if (this.fileChooser.showOpenDialog(this) == 0) {
            final File file = this.fileChooser.getSelectedFile();
            final File dir = new File("src/bkapt/com/image");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            final File newFile = new File(dir, file.getName());
            try {
                Files.copy(Paths.get(file.getAbsolutePath(), new String[0]), Paths.get(newFile.getAbsolutePath(), new String[0]), StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            ImageIcon resizedImage = resize(new ImageIcon(newFile.getAbsolutePath()), 126, 152);
            this.lblHinh.setIcon(resizedImage);
            this.lblHinh.setToolTipText(newFile.getName());
            
        }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void pnlAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlAnhMouseClicked
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);
    }//GEN-LAST:event_pnlAnhMouseClicked

    private void cboTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTheLoaiActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        loadTen();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtMaSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSachKeyReleased
        if(!txtMaSach.getText().equals("")){
            lblMaSach1.setText(null);
        }
    }//GEN-LAST:event_txtMaSachKeyReleased

    private void txtTenSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSachKeyReleased
        if(!txtTenSach.getText().equals("")){
            lblTenSach1.setText(null);
        }
    }//GEN-LAST:event_txtTenSachKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        if(!txtSoLuong.getText().equals("")){
            lblSoLuong1.setText(null);
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtNoiDungKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoiDungKeyReleased
        if(!txtNoiDung.getText().equals("")){
            lblNoiDung1.setText(null);
        }
    }//GEN-LAST:event_txtNoiDungKeyReleased

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        delete();
        load();
    }//GEN-LAST:event_btnXoa1ActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNXB;
    private javax.swing.JComboBox<String> cboTacGia;
    private javax.swing.JComboBox cboTheLoai;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblMaSach1;
    private javax.swing.JLabel lblNXB;
    private javax.swing.JLabel lblNXB1;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblNoiDung1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblSoLuong1;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JLabel lblTacGia1;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTenSach1;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblTheLoai1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAnh;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNoiDung;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
