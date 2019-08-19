package de.webis.clueweb_filter;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;

import lombok.SneakyThrows;

public class Clueweb12B13Filter {

	private static final byte[] DUMMY_VALUE = toBytes("a");

	private static final String DB_PATH = ".clueweb-12b13-ids-rocksdb";

	private static final List<String> TEST_IDS = Collections.unmodifiableList(Arrays.asList(
			"clueweb12-1914wb-28-24250", "clueweb12-0000tw-00-00013"
		));
	
	private static final RocksDB ROCKS_DB = rocksDbWithIds();
	
	@SneakyThrows
	private static RocksDB rocksDbWithIds() {
		RocksDB db;
		
		if(dbExists()) {
			db = createTemporaryRocksDb();
			
			if(TEST_IDS.stream().allMatch(id -> idIsClueweb12B13(db, id))) {
				return db;
			}
			
		}
		else {
			db = createTemporaryRocksDb();
		}
		
		List<String> ids = readAllIds();
		
		for (String id : ids) {
			db.put(toBytes(id), DUMMY_VALUE);
		}

		return db;
	}
	
	@SneakyThrows
	public static List<String> readAllIds() {
		InputStream resource = Clueweb12B13Filter.class.getResourceAsStream("../../../clueweb-12-b13-ids.txt");
		return IOUtils.readLines(resource, StandardCharsets.UTF_8);
	}

	@SneakyThrows
	private static RocksDB createTemporaryRocksDb() {
		RocksDB.loadLibrary();
		Options options = new Options();
		options.setCreateIfMissing(Boolean.TRUE);
		
		return RocksDB.open(options, DB_PATH);
	}

	private static boolean dbExists() {
		return new File(DB_PATH).exists()
			&& new File(DB_PATH).list().length > 0;
	}
	
	@SneakyThrows
	public static boolean idIsClueweb12B13(String id) {
		return idIsClueweb12B13(ROCKS_DB, id);
	}
	
	@SneakyThrows
	private static boolean idIsClueweb12B13(RocksDB db, String id) {
		return db.get(toBytes(id)) != null;
	}

	private static byte[] toBytes(String v) {
		return v.getBytes(StandardCharsets.UTF_8);
	}
}
