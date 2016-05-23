package com.m2r.boteutils.flatfile;

import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
			flatFile.load(reader);
			if (flatFile.hasNextRecord()) {
				HeaderRetorno header = (HeaderRetorno) flatFile.nextRecord();
				Assert.assertNotNull(header);
				Assert.assertEquals(header.getId(), new Integer(0));
				Assert.assertEquals(header.getSequencialRegistro(), new Integer(1));
			}
			if (flatFile.hasNextRecord()) {
				DetailRetorno detail = (DetailRetorno) flatFile.nextRecord();
				Assert.assertNotNull(detail);
				Assert.assertEquals(detail.getId(), new Integer(7));
				Assert.assertEquals(detail.getSequencialRegistro(), new Integer(2));
			}
			if (flatFile.hasNextRecord()) {
				DetailRetorno detail = (DetailRetorno) flatFile.nextRecord();
				Assert.assertNull(detail);
			}
			if (flatFile.hasNextRecord()) {
				TraillerRetorno trailler = (TraillerRetorno) flatFile.nextRecord();
				Assert.assertNotNull(trailler);
				Assert.assertEquals(trailler.getId(), new Integer(9));
				Assert.assertEquals(trailler.getSequencialRegistro(), new Integer(4));
			}
			Assert.assertFalse(flatFile.hasNextRecord());

		} catch (FlatFileException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void test2() throws FileNotFoundException {
		InputStream in = new FileInputStream("C:\\Users\\raimundo.botelho\\Downloads\\IEDCBR3051705201610655.ret");
		Reader reader = new InputStreamReader(in);

		FlatFile flatFile = new FlatFile();

		flatFile.registerRecord(com.m2r.boteutils.flatfile.modelo2.HeaderRetorno.class);
		flatFile.registerRecord(com.m2r.boteutils.flatfile.modelo2.HeaderLoteRetorno.class);
		flatFile.registerRecord(com.m2r.boteutils.flatfile.modelo2.DetailRetorno.class);

		try {
			flatFile.load(reader);
			while (flatFile.hasNextRecord()) {
				System.out.println(flatFile.nextRecord());
			}
		} catch (FlatFileException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
