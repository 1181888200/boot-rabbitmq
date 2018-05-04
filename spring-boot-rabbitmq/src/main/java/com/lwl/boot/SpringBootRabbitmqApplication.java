package com.lwl.boot;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitmqApplication {

	
	 /***简单的队列模式***/
	/**
	 * 　Direct是RabbitMQ默认的交换机模式,也是最简单的模式.
	 * 		即创建消息队列的时候,指定一个BindingKey.当发送者发送消息的时候,
	 * 		指定对应的Key.当Key和消息队列的BindingKey一致的时候,消息将会被发送到该消息队列中.
	 * 
	 * Direct模式相当于一对一模式,一个消息被发送者发送后,会被转发到一个绑定的消息队列中,然后被一个接收者接收!
	 */
	 @Bean
	 public Queue simpleHelloQueue() {
		 return new Queue("simple");
	 }
	 
	/** 
	 * 实际上RabbitMQ还可以支持发送对象:当然由于涉及到序列化和反序列化,该对象要实现Serilizable接口
	 * 支持发送消息为对象
	 *		实体类（必须实现序列化接口）
	 */
    @Bean
    public Queue userQueue() {
        return new Queue("userQueue");
    }
	 
	 
	    //===============以下是验证topic Exchange的队列==========
    	/**
    	 * topic转发信息主要是依据通配符,
    	 * 		队列和交换机的绑定主要是依据一种模式(通配符+字符串),
    	 * 		而当发送消息的时候,只有指定的Key和该模式相匹配的时候,消息才会被发送到该消息队列中.
    	 * 
	     *  this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);
    	 * 		方法的第一个参数是交换机名称,第二个参数是发送的key,第三个参数是内容
	     *   	RabbitMQ将会根据第二个参数去寻找有没有匹配此规则的队列,如果有,则把消息给它,如果有不止一个,则把消息分发给匹配的队列(每个队列都有消息!)
    	 */
	    @Bean
	    public Queue queueMessage() {
	        return new Queue("topic.message");
	    }

	    @Bean
	    public Queue queueMessages() {
	        return new Queue("topic.messages");
	    }
	    
	    @Bean
	    TopicExchange exchange() {
	        return new TopicExchange("exchange");
	    }
	    
	    /**
	     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
	     * @param queueMessage
	     * @param exchange
	     * @return
	     */
	    @Bean
	    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
	        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	    }

	    /**
	     * 将队列topic.messages与exchange绑定，binding_key为topic.mesaages,模糊匹配
	     * @param queueMessage
	     * @param exchange
	     * @return
	     */
	    @Bean
	    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
	        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.mesaages");
	    }
	    
	  //===============以上是验证topic Exchange的队列==========
	    
	    
	    //===============以下是验证Fanout Exchange的队列==========
	    /**
	     * Fanout Exchange形式又叫广播形式,
	     * 		因此我们发送到路由器的消息会使得绑定到该路由器的每一个Queue接收到消息,
	     * 		这个时候就算指定了Key,或者规则(即上文中convertAndSend方法的参数2),也会被忽略!
	     * 
	     * 	A和B 是订阅的fanoutExchange，所以当一条信息发送给fanoutExchange时，A和B都将会收到信息
	     * 	C 订阅的是fanoutExchange2，所以当一条信息发送给fanoutExchange2时，C将会收到信息 , A和B收不到信息
	     * 
	     */
	    @Bean(name="AMessage")
	    public Queue AMessage() {
	        return new Queue("fanout.A");
	    }

	    @Bean(name="BMessage")
	    public Queue BMessage() {
	        return new Queue("fanout.B");
	    }

	    @Bean(name="CMessage")
	    public Queue CMessage() {
	        return new Queue("fanout.C");
	    }
	    
	    @Bean(name="fanoutExchange")
	    FanoutExchange fanoutExchange() {
	        return new FanoutExchange("fanoutExchange");
	    }
	    
	    @Bean(name="fanoutExchange2")
	    FanoutExchange fanoutExchange2() {
	    	return new FanoutExchange("fanoutExchange2");
	    }
	    
	    @Bean
	    Binding bindingExchangeA(@Qualifier("AMessage")Queue AMessage,FanoutExchange fanoutExchange) {
	        return BindingBuilder.bind(AMessage).to(fanoutExchange);
	    }

	    @Bean
	    Binding bindingExchangeB(@Qualifier("BMessage")Queue BMessage, FanoutExchange fanoutExchange) {
	        return BindingBuilder.bind(BMessage).to(fanoutExchange);
	    }

	    @Bean
	    Binding bindingExchangeC(@Qualifier("CMessage")Queue CMessage, @Qualifier("fanoutExchange2")FanoutExchange fanoutExchange2) {
	        return BindingBuilder.bind(CMessage).to(fanoutExchange2);
	    }
	    
	    //===============以上是验证Fanout Exchange的队列==========

    //===============以下是验证callBack==========
	    @Bean
	    public Queue queueCallBack() {
	        return new Queue("topic.callback");
	    }
	    
    //===============以上是验证callBack==========
	 
		 
		public static void main(String[] args) {
			SpringApplication.run(SpringBootRabbitmqApplication.class, args);
		}
}
