package com.ssh.nisus.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/*

lk_name
lkm_gender
lkm_phone
lkm_mobile


 */


/**
 * 联系人bean
 */
@Entity
@Table(name = "cst_linkman", schema = "crm_hibernate", catalog = "")
@GenericGenerator(name = "genLkmId" , strategy = "native")
public class Linkman {
    @Id
    @GeneratedValue(generator = "genLkmId")
    private long lkm_id;
    private String lk_name;
    private String lkm_gender;
    private String lkm_phone;
    private String lkm_mobile;
    @ManyToOne()
    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    private Customer customer = new Customer();

    public long getLkm_id() {
        return lkm_id;
    }

    public void setLkm_id(long lkm_id) {
        this.lkm_id = lkm_id;
    }

    public String getLk_name() {
        return lk_name;
    }

    public void setLk_name(String lk_name) {
        this.lk_name = lk_name;
    }

    public String getLkm_gender() {
        return lkm_gender;
    }

    public void setLkm_gender(String lkm_gender) {
        this.lkm_gender = lkm_gender;
    }

    public String getLkm_phone() {
        return lkm_phone;
    }

    public void setLkm_phone(String lkm_phone) {
        this.lkm_phone = lkm_phone;
    }

    public String getLkm_mobile() {
        return lkm_mobile;
    }

    public void setLkm_mobile(String lkm_mobile) {
        this.lkm_mobile = lkm_mobile;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
