/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import DTO.*;
import java.sql.*;
import Utils.DBUtils;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author huynh
 */
public class DatPhongUI extends javax.swing.JFrame {

    /**
     * Creates new form DatPhongUI_test
     */
    public DatPhongUI() {
        initComponents();
        dateNgayNhanPhong.setDate(new java.util.Date());
        loadCbbDangThue();
    }

    ArrayList<String> arrDangThue = new ArrayList<String>();
    public void loadCbbDangThue(){
        arrDangThue.removeAll(arrDangThue);
        Connection con = new DBUtils().createConn();
        try{
            String strSQL = "select * from roominformation";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            
            while(rs.next()){
                if(!arrDangThue.contains(rs.getString("roomtypename"))){
                    arrDangThue.add(rs.getString("roomtypename"));
                }
            }
            cbbDangThue.setModel(new DefaultComboBoxModel<String>(arrDangThue.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    ArrayList<String> arrSoPhong = new ArrayList<String>();
    public void loadSoPhong(){
        arrSoPhong.removeAll(arrSoPhong);
        String roomTypeID = new String();
        Connection con = new DBUtils().createConn();
        try{
            String strSQL = "select * from roominformation where roomtypename = '"+cbbDangThue.getSelectedItem()+"'"
                    + " and slotremaining >= "+ Integer.parseInt(txtSLGiuong.getText())
                    + " and roomisfull is not true";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                arrSoPhong.add(Integer.toString(rs.getInt("roomid")));
            }
            cbbSoPhong.setModel(new DefaultComboBoxModel<String>(arrSoPhong.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int layBookingID(){
        int bookingID = 0;
        Connection con = new DBUtils().createConn();
        
        String strSQL = "select bookingid from booking order by bookingid desc";
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            if(rs.next()==false){
                bookingID = 1;
            }
            else{
                int bookingIDHienTai = rs.getInt("bookingid");
                bookingID = bookingIDHienTai+1;
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return bookingID;
    }
    
    public int layDepositID(){
        int depositID = 0;
        Connection con = new DBUtils().createConn();
        
        String strSQL = "select depositid from deposit order by depositid desc";
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            if(rs.next()==false){
                depositID = 1;
            }
            else{
                int depositIDHienTai = rs.getInt("depositid");
                depositID = depositIDHienTai+1;
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return depositID;
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
        paneDatPhong = new javax.swing.JPanel();
        cbbDangThue = new javax.swing.JComboBox<>();
        btnDatCoc = new javax.swing.JButton();
        cbbSoPhong = new javax.swing.JComboBox<>();
        dateNgayDi = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dateNgayNhanPhong = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ckbDatCoc = new javax.swing.JCheckBox();
        btnDatPhong = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        txtSLGiuong = new javax.swing.JTextField();
        paneDatCoc = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtCMNDDC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDienGiai = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTenKHDC = new javax.swing.JTextField();
        txtSoTien = new javax.swing.JTextField();
        btnXacNhanCoc = new javax.swing.JButton();
        btnHuyDC = new javax.swing.JButton();
        dateNgayNhanCoc = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabPanel.setEnabled(false);

        paneDatPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbDangThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDangThueActionPerformed(evt);
            }
        });
        paneDatPhong.add(cbbDangThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 68, 171, 30));

        btnDatCoc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDatCoc.setText("Đặt cọc");
        btnDatCoc.setEnabled(false);
        btnDatCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatCocActionPerformed(evt);
            }
        });
        paneDatPhong.add(btnDatCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 272, 104, 46));

        paneDatPhong.add(cbbSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 164, 171, 30));
        paneDatPhong.add(dateNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 215, 197, 30));

        jLabel9.setText("Số phòng");
        paneDatPhong.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 172, -1, -1));
        paneDatPhong.add(txtCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 116, 201, 30));
        paneDatPhong.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 68, 201, 30));

        jLabel5.setText("Ngày đi");
        paneDatPhong.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 215, -1, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Thông tin phòng");
        paneDatPhong.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 28, -1, -1));

        dateNgayNhanPhong.setEnabled(false);
        paneDatPhong.add(dateNgayNhanPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 164, 197, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Thông tin khách hàng");
        paneDatPhong.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 28, -1, -1));

        jLabel6.setText("CMND");
        paneDatPhong.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, 30));

        jLabel3.setText("Dạng thuê");
        paneDatPhong.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 76, -1, -1));

        ckbDatCoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ckbDatCoc.setText("Đã đặt cọc");
        ckbDatCoc.setEnabled(false);
        ckbDatCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbDatCocActionPerformed(evt);
            }
        });
        paneDatPhong.add(ckbDatCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 272, 120, 46));

        btnDatPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDatPhong.setText("Đặt phòng");
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });
        paneDatPhong.add(btnDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 272, -1, 46));

        jLabel7.setText("Tên khách hàng");
        paneDatPhong.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 68, -1, 30));

        jLabel4.setText("Số lượng giường");
        paneDatPhong.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 124, -1, -1));

        jLabel8.setText("Ngày nhận phòng");
        paneDatPhong.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 164, 98, 30));

        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        paneDatPhong.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 272, 123, 46));

        txtSLGiuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLGiuongActionPerformed(evt);
            }
        });
        paneDatPhong.add(txtSLGiuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 170, 30));

        tabPanel.addTab("Thông tin đặt phòng", paneDatPhong);

        paneDatCoc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("CMND");
        paneDatCoc.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 132, -1, 25));

        txtCMNDDC.setEditable(false);
        paneDatCoc.add(txtCMNDDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 127, 160, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Diễn giải");
        paneDatCoc.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 273, -1, 30));
        paneDatCoc.add(txtDienGiai, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 275, 160, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Thông tin đăt cọc");
        paneDatCoc.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 28, 164, 33));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tên khách hàng");
        paneDatCoc.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 84, -1, 25));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Ngày nhận cọc");
        paneDatCoc.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 175, -1, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Số tiền");
        paneDatCoc.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 223, -1, 30));

        txtTenKHDC.setEditable(false);
        paneDatCoc.add(txtTenKHDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 79, 160, 30));
        paneDatCoc.add(txtSoTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 225, 160, 30));

        btnXacNhanCoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXacNhanCoc.setText("Xác nhận cọc");
        btnXacNhanCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanCocActionPerformed(evt);
            }
        });
        paneDatCoc.add(btnXacNhanCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, 48));

        btnHuyDC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHuyDC.setText("Hủy");
        btnHuyDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDCActionPerformed(evt);
            }
        });
        paneDatCoc.add(btnHuyDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 111, 48));
        paneDatCoc.add(dateNgayNhanCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 175, 160, 30));

        tabPanel.addTab("Thông tin đặt cọc", paneDatCoc);

        getContentPane().add(tabPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 700, 378));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatCocActionPerformed
        // TODO add your handling code here:
        txtTenKHDC.setText(txtTenKH.getText());
        txtCMNDDC.setText(txtCMND.getText());
        dateNgayNhanCoc.setDate(new java.util.Date());
        tabPanel.setSelectedIndex(1);
    }//GEN-LAST:event_btnDatCocActionPerformed

    private void ckbDatCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbDatCocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbDatCocActionPerformed

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
        // TODO add your handling code here:
        if(txtCMND.getText().equals("")||txtTenKH.getText().equals("")||Integer.parseInt(txtSLGiuong.getText())<1||
                dateNgayDi.getDate().equals("")||dateNgayNhanPhong.getDate().equals("")||
                dateNgayDi.getDate().before(dateNgayNhanPhong.getDate())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông tin đặt phòng", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Connection con = new DBUtils().createConn();
            CustomerDTO customer = new CustomerDTO();
            customer.setCustomerID(Integer.parseInt(txtCMND.getText()));
            customer.setCustomerName(txtTenKH.getText());
            CustomerBLL customerBll = new CustomerBLL();
            try{
                int result = customerBll.insertCustomer(customer);
                if(result!=0){
                    BookingDTO booking = new BookingDTO();
                    booking.setBookingID(layBookingID());
                    booking.setCustomerID(Integer.parseInt(txtCMND.getText()));
                    booking.setRoomID(Integer.parseInt(cbbSoPhong.getSelectedItem().toString()));
                    java.util.Date utilDate = dateNgayNhanPhong.getDate();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    booking.setStartDate(sqlDate);
                    utilDate = dateNgayDi.getDate();
                    sqlDate = new java.sql.Date(utilDate.getTime());
                    booking.setEndDate(sqlDate);
                    booking.setSlot(Integer.parseInt(txtSLGiuong.getText()));
                    BookingBLL bookingBll = new BookingBLL();
                    try{
                        int result1 = bookingBll.insertBooking(booking);
                        if(result1!=0){
                            String strUpdate = "update roominformation set slotremaining = slotremaining - "+Integer.parseInt(txtSLGiuong.getText())+""
                                    + " where roomid = "+cbbSoPhong.getSelectedItem()
                                    + " and roomtypename = 'Ở ghép'";
                            con = new DBUtils().createConn();
                            Statement stat = con.createStatement();
                            result1 = stat.executeUpdate(strUpdate);
                            
                            strUpdate = "update roominformation set roomisfull = true "
                                    + "where roomid = "+cbbSoPhong.getSelectedItem()
                                    + " and roomtypename = 'Nguyên phòng'"
                                    + " or slotremaining = 0";
                            con = new DBUtils().createConn();
                            stat = con.createStatement();
                            result1 = stat.executeUpdate(strUpdate);
                            JOptionPane.showMessageDialog(null, "Book thành công, vui lòng ghi nhận đặt cọc","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            btnDatCoc.setEnabled(true);
                            btnDatPhong.setEnabled(false);
                        }else{
                            JOptionPane.showMessageDialog(null, "Book không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
            
            
            
//            BookingDTO booking = new BookingDTO();
//            booking.setBookingID(layBookingID());
//            booking.setCustomerID(Integer.parseInt(txtCMND.getText()));
//            booking.setRoomID(Integer.parseInt(cbbSoPhong.getSelectedItem().toString()));
//            java.util.Date utilDate = dateNgayDi.getDate();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//            booking.setStartDate(sqlDate);
//            utilDate = dateNgayNhanPhong.getDate();
//            sqlDate = new java.sql.Date(utilDate.getTime());
//            booking.setEndDate(sqlDate);
//            BookingBLL bookingBll = new BookingBLL();
//            try{
//                int result = bookingBll.insertBooking(booking);
//                if(result!=0){
//                    String strUpdate = "update roominformation set roomslot = roomslot - "+Integer.parseInt(txtSLGiuong.getText())+""
//                            + " where roomid = "+cbbSoPhong.getSelectedItem()
//                            +" and roomtypename = 'Ở ghép'";
//                    JOptionPane.showMessageDialog(null, "Book thành công, vui lòng ghi nhận đặt cọc","Thông báo",JOptionPane.INFORMATION_MESSAGE);
//                    btnDatCoc.setEnabled(true);
//                    btnDatPhong.setEnabled(false);
//                }else{
//                    JOptionPane.showMessageDialog(null, "Book không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
    }//GEN-LAST:event_btnDatPhongActionPerformed

    private void btnXacNhanCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanCocActionPerformed
        // TODO add your handling code here:
        if(txtTenKHDC.equals("")||txtCMNDDC.equals("")||dateNgayNhanCoc.equals("")||txtSoTien.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Connection con = new DBUtils().createConn();
            String strSQL = "select * from booking where customerid = "+txtCMND.getText();
            try{
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery(strSQL);
                if(rs.next()){
                    DepositDTO deposit = new DepositDTO();
                    deposit.setDepositID(layDepositID());
                    deposit.setBookingID(rs.getInt("bookingid"));
                    deposit.setCustomerID(Integer.parseInt(txtCMNDDC.getText()));
                    java.util.Date utilDate = dateNgayNhanCoc.getDate();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    deposit.setDepositDate(sqlDate);
                    deposit.setDepositAmount(Integer.parseInt(txtSoTien.getText()));
                    deposit.setExplanation(txtDienGiai.getText());

                    DepositBLL depositBll = new DepositBLL();
                    try{
                        int result = depositBll.insertDeposit(deposit);
                        if(result!=0){
                            JOptionPane.showMessageDialog(null, "Thêm thông tin đặt cọc thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            tabPanel.setSelectedIndex(0);
                            this.btnDatCoc.setEnabled(false);
                            this.ckbDatCoc.setSelected(true);
                            this.btnDatPhong.setEnabled(false);
                        }else{
                            JOptionPane.showMessageDialog(null, "Thêm thông tin đặt cọc không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXacNhanCocActionPerformed

    private void btnHuyDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDCActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null, "Hủy đặt cọc", "Đặt cọc", JOptionPane.YES_NO_OPTION);
        if(ret == JOptionPane.YES_OPTION)
            tabPanel.setSelectedIndex(0);
    }//GEN-LAST:event_btnHuyDCActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        if(!this.ckbDatCoc.isSelected()){
            int ret = JOptionPane.showConfirmDialog(null, "Chưa đặt cọc, xác nhận thoát?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if(ret == JOptionPane.YES_OPTION){
                this.setVisible(false);
            }
        }else{
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cbbDangThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDangThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDangThueActionPerformed

    private void txtSLGiuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLGiuongActionPerformed
        // TODO add your handling code here:
        this.loadSoPhong();
    }//GEN-LAST:event_txtSLGiuongActionPerformed

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
            java.util.logging.Logger.getLogger(DatPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatPhongUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatCoc;
    private javax.swing.JButton btnDatPhong;
    private javax.swing.JButton btnHuyDC;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXacNhanCoc;
    private javax.swing.JComboBox<String> cbbDangThue;
    private javax.swing.JComboBox<String> cbbSoPhong;
    private javax.swing.JCheckBox ckbDatCoc;
    private com.toedter.calendar.JDateChooser dateNgayDi;
    private com.toedter.calendar.JDateChooser dateNgayNhanCoc;
    private com.toedter.calendar.JDateChooser dateNgayNhanPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel paneDatCoc;
    private javax.swing.JPanel paneDatPhong;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtCMNDDC;
    private javax.swing.JTextField txtDienGiai;
    private javax.swing.JTextField txtSLGiuong;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKHDC;
    // End of variables declaration//GEN-END:variables
}
