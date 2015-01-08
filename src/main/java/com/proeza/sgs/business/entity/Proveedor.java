package com.proeza.sgs.business.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(schema = "sgs_proeza_db", name = "proveedor")
@PrimaryKeyJoinColumn(name = "fk_persona", referencedColumnName = "id")
public class Proveedor extends Persona {

}