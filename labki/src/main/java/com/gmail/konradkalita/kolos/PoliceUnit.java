package com.gmail.konradkalita.kolos;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@AllArgsConstructor
@ToString
public class PoliceUnit {
  int id;
  int id_gl;
  String nazwa;
  String rodzaj;
  String kod;
  String miasto;
  String ulica;
  String kier;
  String tel;
  String fax;
  String internet;
  String mail;

  // stream().sorted(
  //  Comparator.comparing((TemplateUnit unit) -> unit.name)
  //  .thenComparing(unit -> unit.name)
  //  .thenComparing(unit -> unit.surname) )
}
