package com.haog.boot.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//扫描mapper文件夹
@MapperScan("com.haog.boot.sms")
@EnableTransactionManagement // 自动管理事务
@Configuration // 配置类
public class MybatisPlusConfig {
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor ( );
    // 注册乐观锁插件
    interceptor.addInnerInterceptor (new OptimisticLockerInnerInterceptor ( ));

    // 分页插件
    PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor ( );
    pageInterceptor.setOverflow (false); // 设置请求的页面大于最大页后操作，true调回到首页，false继续请求。默认false
    pageInterceptor.setMaxLimit (500L); // 单页分页条数限制，默认无限制
    pageInterceptor.setDbType (DbType.MYSQL); // 设置数据库类型
    interceptor.addInnerInterceptor (pageInterceptor);

    return interceptor;
  }

  // 注册逻辑删除
  @Bean
  public ISqlInjector sqlInjector(){
    return new DefaultSqlInjector ();
  }
}
