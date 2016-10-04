package com.m2r.boteutils.flatfile;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import com.m2r.boteutils.flatfile.model.DetalheRetorno;
import com.m2r.boteutils.flatfile.model.HeaderRetorno;
import com.m2r.boteutils.flatfile.model.TraillerRetorno;
import com.m2r.flatfile.FlatFile;
import com.m2r.flatfile.exception.FlatFileException;


public class FlatFileTest {

	private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void test1() {
		InputStream in = this.getClass().getResourceAsStream("file.ret");
		Reader reader = new InputStreamReader(in);

		FlatFile flatFile = new FlatFile();

		flatFile.registerRecord(HeaderRetorno.class);
		flatFile.registerRecord(DetalheRetorno.class);
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
				DetalheRetorno detail = (DetalheRetorno) flatFile.nextRecord();
				Assert.assertNotNull(detail);
				Assert.assertEquals(detail.getId(), new Integer(7));
				Assert.assertEquals(detail.getSequencialRegistro(), new Integer(2));
			}
			if (flatFile.hasNextRecord()) {
				DetalheRetorno detail = (DetalheRetorno) flatFile.nextRecord();
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
	public void test2() {
		InputStream in = this.getClass().getResourceAsStream("CBR6433142405201610409.ret");
		Reader reader = new InputStreamReader(in);

		FlatFile flatFile = new FlatFile();

		flatFile.registerRecord(HeaderRetorno.class);
		flatFile.registerRecord(DetalheRetorno.class);
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
				TraillerRetorno trailler = (TraillerRetorno) flatFile.nextRecord();
				Assert.assertNotNull(trailler);
				Assert.assertEquals(trailler.getId(), new Integer(9));
				Assert.assertEquals(trailler.getSequencialRegistro(), new Integer(2));
			}
			Assert.assertFalse(flatFile.hasNextRecord());

		} catch (FlatFileException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	//@Test
	public void test3() {
		InputStream in = this.getClass().getResourceAsStream("CBR643321106201610456.ret");
		Reader reader = new InputStreamReader(in);

		FlatFile flatFile = new FlatFile();

		flatFile.registerRecord(HeaderRetorno.class);
		flatFile.registerRecord(DetalheRetorno.class);
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
				DetalheRetorno detalhe = (DetalheRetorno) flatFile.nextRecord();
				Assert.assertNotNull(detalhe);
				Assert.assertEquals(detalhe.getId(), new Integer(7));
				Assert.assertEquals(detalhe.getCarteira(), "17");
				Assert.assertNull(detalhe.getDataVencimento());
				Assert.assertEquals(dateformat.format(detalhe.getDataLiquidacao()), "01/06/2016");
				Assert.assertEquals(detalhe.getValorTitulo(), new BigDecimal("3.00"));
				Assert.assertEquals(detalhe.getSequencialRegistro(), new Integer(3));
			}
			if (flatFile.hasNextRecord()) {
				TraillerRetorno trailler = (TraillerRetorno) flatFile.nextRecord();
				Assert.assertNotNull(trailler);
				Assert.assertEquals(trailler.getId(), new Integer(9));
				Assert.assertEquals(trailler.getSequencialRegistro(), new Integer(2));
			}
			Assert.assertFalse(flatFile.hasNextRecord());

		} catch (FlatFileException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
