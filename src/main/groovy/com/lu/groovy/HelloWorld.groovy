package com.lu.groovy

import com.sun.xml.bind.v2.schemagen.Tree.Repeated;

class HelloWorld
{
	static void main(def args){
      println "Hello World"
	  
	  def var = "你好";	  
	  println var //Groovy中没有语句结束符，当然为了与java保持一致性，你也可以使用;号作为语句结束符。在前面的每一句代码后面加上;号结束，程序同样正常运行(为了接受java程序员的顽固习惯)。
	  println var.class;//var的实际类型为：java.lang.String
	  
	  var = 100
	  println var
	  println var.class;
	  
	  //当然更groovy的写法是：返回多行
	  var="""hello
       world
       groovy2!"""
	   println var
	   
	  //跟java一样，如果你需要把一个字符串写在一行里，可以使用+号连接字符串。代码可以这样写：
	  var="hello "+	  "world"+	  ",groovy1!"
	  println var
	  
	   //for 循环
	   for(i in 1..3){//包括 1，2, 3
		   //println var;
		   println "This is ${i}:${var}"
	   }
	   
	   println "==============循环========================="
	   for(i in 1..<3){ //不包括3
		   //println var;
		   println "This is ${i}:${var}"
	   }
	   
	   println "==================方法====================="
	   repeat(var)//默认返回次数3次，repeat(var，8) 则返回循环8次
	   
	   println "======================集合================="
	   def coolect = ["a","b","c","d"];
	   //三种方式给集合中添加元素
	   coolect.add("add");
	   coolect << "<<";
	   coolect[coolect.size()] = "size"
	   println coolect;
	   
	   println coolect[coolect.size() - 1];
	   println coolect[3];
	   
	   println coolect[-1]      //索引其倒数第1个元素
	   println coolect[-2]      //索引其倒数第2个元素
    
	   coolect = coolect +5;//在集合中添加元素5
	   coolect = coolect - "a"; //在集合中减去元素a
	   println coolect;
	   
	   coolect = coolect - coolect[0..4];
	   println coolect[0];
	   println coolect[-1];
	   
	   println "======================Map================="
	   def map = ["name":"john","age":12,"sex":"boy"];
	   println map;
	   map = map + ['weight':56];
	   map.put('birth', '2011-03-06');
	   map.father = 'sany';
	   println map['father'];
	   println map.birth;
	   
	   println "======================闭包================="
	   map.each({key,value ->
		   println "$key:$value"
	   }) 
	   
	   println "Map each it================="
	   map.each {println it};
	   map.each ({it.getKey() + "=" + it.getValue()})
	   
	   //自定义闭包
	   def say = { word ->
		   println "hi,${word}";		   
	   };
	   say("groovy");
	   
	   
	   println "====================可变参数============================"
	   //println sum(1); //int... 不支持，不知道是不是groovy版本不支持
	   
	   println "====================枚举定义============================"
	   def today  = Day.FRIDAY;
	   
	   switch(today){
		   case [Day.SUNDAY, Day.SATURDAY]:
		   	println "weekday";
			break;
		   case Day.MONDAY..Day.FRIDAY:
		   println "workday";
		   break;
		   default:println "Are you sure this is a valid day?"
	   }
	   
	   println "====================三目操作符============================"
	   def disname = 50;
	   def displayName = disname ?: "Unknown"; //disname != null 值为disname,否则为unknown
	   println displayName
	   
	   println "====================动态性============================"
	   def msg = "Hello"
	   println msg.getMetaClass();	
	   String.metaClass.up = {  delegate.toUpperCase() }
	   println msg.up();
	   
	   println "=动态性=== 反射方法========================="
	   msg.metaClass.metaMethods.each {println it.name};
	   println "=动态性=== 反射属性========================="
	   msg.metaClass.properties.each {println it.name}
	  
	   //我们可以通过元类判断有没有一个叫up的方法，然后再调用它：
	   msg.metaClass.respondsTo(msg, "up"){
		   println msg.toUpperCase();
	   } 
	   //也可以推断它有没有一个叫bytes的属性：not work
//	   msg.metaClass.hasProperty(msg, "bytes"){
//		   println msg.bytes.encodeBase64();
//	   }
	} 
	
	def static repeat(var, repeat = 3){
		for(i in 0..<repeat){
			def println = println "This is ${i}:${var}"
		   }
	}
	
//	def static sum(int... var) {
//		def total = 0
//		for (i in var)
//			total += i
//		return total
//	}
}
