package com.iskech.mycollection.list.impl;

public enum MyEnum {
	
  ONE("xx","xx"),
  TWO("xx","xx"),
  THREE("xx","xx");
  
  
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	MyEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

  public static void main(String[] args) {
    for (MyEnum arg :MyEnum.values()) {
      System.out.println(arg.getKey());
    }
   
  }
}
