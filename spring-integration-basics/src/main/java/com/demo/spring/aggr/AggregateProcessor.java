package com.demo.spring.aggr;

import java.util.List;

public class AggregateProcessor {


	  public String aggregate(List<String> words) {
	  
	  return words.stream().reduce((s1,s2)->s1.concat(" "+s2)).get(); }
	 

}
