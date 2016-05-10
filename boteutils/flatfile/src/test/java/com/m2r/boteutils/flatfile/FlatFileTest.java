package com.m2r.boteutils.flatfile;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.m2r.boteutils.flatfile.model.DetailRetorno;
import com.m2r.boteutils.flatfile.model.HeaderRetorno;
import com.m2r.boteutils.flatfile.model.TraillerRetorno;
import com.m2r.flatfile.FlatFile;
import com.m2r.flatfile.exception.FlatFileException;


public class FlatFileTest {

	@Test
	public void test() {
		InputStream in = this.getClass().getResourceAsStream("file.ret");
		Reader reader = new InputStreamReader(in);

		FlatFile flatFile = new FlatFile();

		flatFile.registerRecord(HeaderRetorno.class);
		flatFile.registerRecord(DetailRetorno.class);
		flatFile.registerRecord(TraillerRetorno.class);

		try {
			Iterator<Object> iterator = flatFile.load(reader);
			if (iterator.hasNext()) {
				HeaderRetorno header = (HeaderRetorno) iterator.next();
				Assert.assertNotNull(header);
				Assert.assertEquals(header.getId(), new Integer(0));
				Assert.assertEquals(header.getSequencialRegistro(), new Integer(1));
			}
			if (iterator.hasNext()) {
				DetailRetorno detail = (DetailRetorno) iterator.next();
				Assert.assertNotNull(detail);
				Assert.assertEquals(detail.getId(), new Integer(7));
				Assert.assertEquals(detail.getSequencialRegistro(), new Integer(2));
			}
			if (iterator.hasNext()) {
				DetailRetorno detail = (DetailRetorno) iterator.next();
				Assert.assertNull(detail);
			}
			if (iterator.hasNext()) {
				TraillerRetorno trailler = (TraillerRetorno) iterator.next();
				Assert.assertNotNull(trailler);
				Assert.assertEquals(trailler.getId(), new Integer(9));
				Assert.assertEquals(trailler.getSequencialRegistro(), new Integer(4));
			}

		} catch (FlatFileException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
