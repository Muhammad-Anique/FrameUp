package com.example.FrameUpServer.Model.SocietyParticipation;


import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import jdk.jfr.DataAmount;
import net.bytebuddy.asm.Advice;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@DataAmount

@Entity
public class SocietyParticipation {

    private String rollNo;
}
