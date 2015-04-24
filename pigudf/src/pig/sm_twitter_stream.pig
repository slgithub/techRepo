register jar/hbase-0.94.2.jar;
register jar/zookeeper-3.4.3.jar;
register jar/protobuf-java-2.4.0a.jar;
register jar/guava-11.0.2.jar;
register jar/pig-0.10.0-withouthadoop.jar;
register jar/gson-2.2.2.jar;
register twudf_org_tw.jar;

set fs.default.name hdfs://sangamt4:8020;
set mapred.job.tracker sangamt4:8021;
set hbase.cluster.distributed true;
set hbase.zookeeper.quorum sangamt4;
set hbase.master sangamt4:60000;
set hbase.zookeeper.property.clientPort 2181;
set default_parallel 1;


L1 = LOAD '/export/home/hadoop/sm_data/tech';
L2 = FOREACH L1 GENERATE com.wipro.social.jason.perser.TwitterUdfJson($0) AS line;
L3 = FILTER L2 by line IS NOT null;

L4 = FOREACH L1 GENERATE com.wipro.social.jason.perser.ReTwitterUdfJson($0) AS line;
L5 = FILTER L4 by line IS NOT null;

STORE L3 INTO '/export/home/hadoop/process_stag1' USING PigStorage('|');
STORE L5 INTO '/export/home/hadoop/process_stag1_RE' USING PigStorage('|');
