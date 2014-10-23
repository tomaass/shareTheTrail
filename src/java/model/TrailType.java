/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public enum TrailType {
    XC("XC"), DH("DH"), FR("FR"), DIRT("DIRT");
private final String label;

  private TrailType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }
}
