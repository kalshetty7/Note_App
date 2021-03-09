package com.note.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.note.entities.Base;
import com.note.entities.Note;

public class Utils {

	public static <T extends Base> List<T> clone(List<T> tl) {
		List<T> tl1 = new ArrayList<>();
		for (T t : tl)
			tl1.add(clone(t));
		return tl1;
	}

	public static <T extends Base> T clone(T t) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			os = new ObjectOutputStream(bos);
			os.writeObject(t);
			bis = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bis);
			T clonedOB = (T) ois.readObject();
			clonedOB.setId(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void removeElements(List items, List<Integer> indices) {
		Iterator it = items.iterator();
		int counter = 0;
		while (it.hasNext()) {
			it.next();
			if (indices.contains(counter))
				it.remove();
			counter++;
		}
	}

	public static void setTitle(Note n) {
		String t = Jsoup.parse(n.getText()).text();
		t = t.split(" ")[0];
		t = t.trim();
		if (StringUtils.isEmpty(t))
			t = "Note";
		n.setTitle(t);
	}

	static void p(Object o) {
		System.out.print("\n" + o + "\n");
	}

}
