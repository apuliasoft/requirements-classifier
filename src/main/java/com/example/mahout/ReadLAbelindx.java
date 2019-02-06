package com.example.mahout;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.mahout.common.Pair;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileIterable;

import java.util.HashMap;
import java.util.Map;

public class ReadLAbelindx {
    private static Map<Integer, Long> readDocumentFrequency(Configuration conf, Path documentFrequencyPath) {
        Map<Integer, Long> documentFrequency = new HashMap<Integer,Long>();
        for (Pair<IntWritable, LongWritable> pair: new SequenceFileIterable<IntWritable, LongWritable>(documentFrequencyPath, true, conf)) {
            documentFrequency.put(pair.getFirst().get(), pair.getSecond().get());
        }
        return documentFrequency;
    }

    public static void main(String args[]) {
        Configuration configuration = new Configuration();

        Map<Integer, Long> freqs = readDocumentFrequency(configuration, new Path("./tmpFiles/labelindex"));
        Map<Integer, Long> freqs2 = readDocumentFrequency(configuration, new Path("./data/postman/labelindex"));

        String a = "";

    }
}
