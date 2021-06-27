/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.ServiceBillBLL;
import DTO.ServiceBillDTO;
import Utils.DBUtils;
import java.util.ArrayList;
import java.sql.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huynh
 */
public class DatDichVuUI extends javax.swing.JFrame {

    /**
     * Creates new form DatDichVuUI_test
     */
    public DatDichVuUI() {
        initComponents();
        dateNgayDat.setDate(new java.util.Date());
        loadCbbToDichVu();
        loadTableCTDV();
        loadTenDV();
    }

    
    public ArrayList<String> arrToDV = new ArrayList<String>();
    
    public void loadCbbToDichVu(){
        arrToDV.removeAll(arrToDV);
        Connection con = new DBUtils().createConn();
        try{
            String strSQL = "select * from service";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            
            while(rs.next()){
                if(!arrToDV.contains(rs.getString("departmentname"))){
                    arrToDV.add(rs.getString("departmentname"));
                }
            }
            cbbToDV.setModel(new DefaultComboBoxModel<String>(arrToDV.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    DefaultTableModel tblModelCTDV;
    public void loadTableCTDV(){
        tblModelCTDV = new DefaultTableModel();
        String tieuDe[] = {"Tên dịch vụ", "Số lượng", "Đơn vị tính", "Đơn giá", "Thành tiền"};
        tblModelCTDV.setColumnIdentifiers(tieuDe);
        tblCTDV.setModel(tblModelCTDV);
        setVisible(true);
    }
    
    
    ArrayList<String> arrTenDV = new ArrayList<String>();
    public void loadTenDV(){
        arrTenDV.removeAll(arrTenDV);
        spnSoLuong.setValue(0);
        Connection con = new DBUtils().createConn();
        try{
            String strSQL = "select * from service where departmentname = '"+cbbToDV.getSelectedItem()+"'";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                arrTenDV.add(rs.getString("servicename"));
            }
            cbbTenDV.setModel(new DefaultComboBoxModel<String>(arrTenDV.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int layServiceBillID(){
        int serviceBillID = 0;
        Connection con = new DBUtils().createConn();
        
        String strSQL = "select ServiceBillID from serviceBill order by ServiceBillID desc";
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            if(rs.next()==false){
                serviceBillID = 1;
            }
            else{
                int serviceBillIDHienTai = rs.getInt("ServiceBillID");
                serviceBillID = serviceBillIDHienTai+1;
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return serviceBillID;
    }
    
    public int layStaffID(){
        int staffID = 0;
        ArrayList<Integer> arrStaffID = new ArrayList<Integer>();
        try{
            Connection con = new DBUtils().createConn();
            String strSQL = "select staffid from staff";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                arrStaffID.add(Integer.parseInt(rs.getString("staffid")));
            }
            staffID = arrStaffID.get(new Random().nextInt(arrStaffID.size()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return staffID;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnChiTietDV = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        dateNgayDat = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtSoPhong = new javax.swing.JTextField();
        cbbTenKH = new javax.swing.JComboBox<>();
        cbbToDV = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtDonViTinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTDV = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        cbbTenDV = new javax.swing.JComboBox<>();
        btnXacNhan = new javax.swing.JButton();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabPanel.setEnabled(false);
        tabPanel.setMinimumSize(new java.awt.Dimension(700, 440));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Đặt dịch vụ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Số phòng");

        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        btnChiTietDV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChiTietDV.setText("Chi tiết dịch vụ");
        btnChiTietDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietDVActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tổ dịch vụ");

        dateNgayDat.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ngày đặt");

        txtSoPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoPhongActionPerformed(evt);
            }
        });

        cbbTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenKHActionPerformed(evt);
            }
        });

        cbbToDV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbToDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổ dịch vụ" }));
        cbbToDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbToDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnChiTietDV, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(dateNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cbbToDV, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(dateNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbToDV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChiTietDV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tabPanel.addTab("Đặt dịch vụ", jPanel1);

        txtDonViTinh.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tên dịch vụ");

        txtThanhTien.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Số lượng");

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblCTDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCTDV);

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnQuayLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        cbbTenDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenDVActionPerformed(evt);
            }
        });

        btnXacNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        spnSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSoLuongStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Đơn giá");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Thành tiền");

        txtDonGia.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Chi tiết dịch vụ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel8)
                                .addGap(38, 38, 38)
                                .addComponent(cbbTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel9)
                                .addGap(47, 47, 47)
                                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel12)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        tabPanel.addTab("Chi tiết", jPanel2);

        getContentPane().add(tabPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChiTietDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietDVActionPerformed
        // TODO add your handling code here:
        if(txtSoPhong.getText().equals("")||cbbTenKH.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông tin, ngày tháng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else{
            loadTenDV();
            tabPanel.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnChiTietDVActionPerformed

    public ArrayList<String> arrTenKH = new ArrayList<String>();
    private void txtSoPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoPhongActionPerformed
        // TODO add your handling code here:
        String strSQL = "select booking.customerid, customername from booking, customer where "
                + "roomid = "+txtSoPhong.getText()+ " and booking.customerid = customer.customerid";
        Connection con = new DBUtils().createConn();
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                arrTenKH.add(rs.getString("customername"));
            }
            cbbTenKH.setModel(new DefaultComboBoxModel<String>(arrTenKH.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSoPhongActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        this.tabPanel.setSelectedIndex(0);
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void cbbToDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbToDVActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbbToDVActionPerformed

    private void cbbTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTenKHActionPerformed

    private void cbbTenDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenDVActionPerformed
        // TODO add your handling code here:
        try{
            String strSQL = "select * from service where servicename = '"+cbbTenDV.getSelectedItem().toString()+"'";
            Connection con = new DBUtils().createConn();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            if(rs.next()){
                txtDonViTinh.setText(rs.getString("unit"));
                txtDonGia.setText(rs.getString("unitprice").toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbTenDVActionPerformed

    private void spnSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSoLuongStateChanged
        // TODO add your handling code here:
        double sum = Double.parseDouble(spnSoLuong.getValue().toString())*Double.parseDouble(txtDonGia.getText());
        txtThanhTien.setText(Double.toString(sum));
    }//GEN-LAST:event_spnSoLuongStateChanged

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        Object row[] = {cbbTenDV.getSelectedItem(),spnSoLuong.getValue(), 
            txtDonViTinh.getText(), txtDonGia.getText(), txtThanhTien.getText()};
        tblModelCTDV.addRow(row);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int index = tblCTDV.getSelectedRow();
        tblModelCTDV.removeRow(index);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        try{
            for(int i=0; i<tblCTDV.getRowCount();i++){
                Connection con = new DBUtils().createConn();
                String strSQL = "select serviceID, bookingid, customer.customerid from service, booking, customer"
                        + " where servicename = '"+tblCTDV.getValueAt(i, 0)+"'"
                        + " and customername = '"+cbbTenKH.getSelectedItem().toString()+"'"
                        + " and roomid = "+txtSoPhong.getText()
                        + " and booking.customerid = customer.customerid";
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery(strSQL);
                
                
                
                ServiceBillDTO serviceBill = new ServiceBillDTO();
                if(rs.next()){
                    serviceBill.setServiceBillID(layServiceBillID());
                    serviceBill.setBookingID(rs.getInt("bookingid"));
                    serviceBill.setStaffID(layStaffID());
                    serviceBill.setServiceID(rs.getInt("serviceid"));
                }
                java.util.Date utilDate = new java.util.Date(dateNgayDat.getDate().getTime());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                serviceBill.setServiceDate(sqlDate);
                serviceBill.setQuantity(Integer.parseInt(tblCTDV.getValueAt(i, 1).toString()));
                ServiceBillBLL serviceBillBll = new ServiceBillBLL();
                int result = serviceBillBll.insertServiceBill(serviceBill);
                if(result==0){
                    JOptionPane.showMessageDialog(null, "Đặt dịch vụ không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "Đặt dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnXacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(DatDichVuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatDichVuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatDichVuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatDichVuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatDichVuUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietDV;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbTenDV;
    private javax.swing.JComboBox<String> cbbTenKH;
    private javax.swing.JComboBox<String> cbbToDV;
    private com.toedter.calendar.JDateChooser dateNgayDat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTable tblCTDV;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonViTinh;
    private javax.swing.JTextField txtSoPhong;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
