项目地址 : http://git.oschina.net/free/Mybatis_PageHelper

		<!-- 分页插件 -->
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper</artifactId>
			<version>3.4.2</version>
		</dependency>
		<dependency>
			<groupId>com.github.jsqlparser</groupId>
			<artifactId>jsqlparser</artifactId>
			<version>0.9.1</version>
		</dependency>



        PageHelper.startPage(page, rows, true);//设置分页信息
        
        List<Item> items = itemMapper.queryListOrderByUpdated();//按照更新时间倒序排序
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);
        
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

