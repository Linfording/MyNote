��Ŀ��ַ : http://git.oschina.net/free/Mybatis_PageHelper

		<!-- ��ҳ��� -->
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



        PageHelper.startPage(page, rows, true);//���÷�ҳ��Ϣ
        
        List<Item> items = itemMapper.queryListOrderByUpdated();//���ո���ʱ�䵹������
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);
        
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

