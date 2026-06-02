package com.sist.anno;

@Controller
public class ReplyModel {
	@RequestMapping("reply/list.do")
	public void reply_list()
	{
		System.out.println("reply_list call()...");
	}
	@RequestMapping("reply/insert.do")
	public void reply_insert()
	{
		System.out.println("reply_insert call()...");
	}
	@RequestMapping("reply/update.do")
	public void reply_update()
	{
		System.out.println("reply_update call()...");
	}
	@RequestMapping("reply/delete.do")
	public void reply_delete()
	{
		System.out.println("reply_delete call()...");
	}
}
