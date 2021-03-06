package cn.itcast.consumer.web;

import cn.itcast.consumer.client.UserClient;
import cn.itcast.consumer.pojo.User;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



/**
 * ConsumerController
 * hasee
 * 2019/1/27
 * 18:47
 *
 * @Version 1.0
 **/
@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

   // @Autowired
    //private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

//    @GetMapping("{id}")
////    @HystrixCommand(
////            commandProperties = {
////                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
////            })
//    @HystrixCommand(
//            commandProperties = {
//                    /*
//                    *
//                    * （1）hystrix.command.default.circuitBreaker.requestVolumeThreshold（当在配置时间窗口内达到此数量的失败后，进行短路。默认20个，简言之，10s内请求失败数量达到20个，断路器开。
//                    *
//	                * （2）hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds（短路多久以后开始尝试是否恢复，默认5s）
//                    *
//	                * （3）hystrix.command.default.circuitBreaker.errorThresholdPercentage（出错百分比阈值，当达到此阈值后，开始短路。默认50%）
//                    *
//                    *
//                    * */
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),          //触发熔断的最小请求次数，默认20
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),      //休眠时长，默认是5秒
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")             //触发熔断的失败请求最小占比，默认是50%
//            }
//    )
//    public String queryById(@PathVariable("id")Long id){
//        String url="http://user-service/user/"+id;
//        String user=restTemplate.getForObject(url, String.class);
//        return user;
//    }

    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id")Long id){
        return userClient.queryById(id);
    }


    /*
    * 失败时调用此方法
    *
    * */
    public String queryByIdFallback(Long id){
        return "不好意思，服务器太拥挤了";
    }


/*    @Autowired
    private RibbonLoadBalancerClient client;*/

  /*  @Autowired
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;*/

//    @GetMapping("{id}")
//    public User queryById(@PathVariable("id")Long id){
//
//        //原始方式：根据服务id获取
//       // List<ServiceInstance> instances=discoveryClient.getInstances("user-service");
//        //从实例中取出ip和端口
//   //     ServiceInstance instance=instances.get(0);
//
//
//        //使用Ribbon的负载均衡算法，它自动帮我们取出一个实例
//        //算法有随机，轮询，hash等方式，默认采用随机
//      //  ServiceInstance instance=client.choose("user-service");
//
//      //  String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
//
//        /*
//        * 这里的url地址通过前面的拦截 器拦截请求，然后将user-service的地址使用负载均衡算法匹配一个
//        * 然后赋值进url
//        * 其作用效果跟上面的额client.choose一样，它是隐藏了这些细节，自动完成而已；
//        * */
//
//        String url="http://user-service/user/"+id;
//
//        User user=restTemplate.getForObject(url, User.class);
//        return user;
//    }
}






















