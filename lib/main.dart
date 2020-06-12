import 'package:flutter/material.dart';
import 'package:test/test.dart';

import 'common/dart/BenchmarkBase.dart';
import 'DeltaBlue/dart/DeltaBlue.dart';
import 'FluidMotion/dart/FluidMotion.dart';
import 'Havlak/dart/Havlak.dart';
import 'Richards/dart/Richards.dart';
import 'Tracer/dart/Tracer.dart';

import 'dart:io' as io;
import 'dart:math' as math;


void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter Benchmark',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyPage(),
    );
  }
}

class MyPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final key = GlobalKey<ScaffoldState>();
    return Scaffold(
      key: key,
      appBar: AppBar(title: Text("Flutter")),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              RaisedButton(
                child: Text("Test"),
                onPressed: () {
                  startPerformance();
                },
              )
            ],
          )
        ],
      ),
    );
  }


  void startPerformance() {
    runBenchmark();
  }


  void runBenchmark() async {
    group('benchmark_harness', () {
      test('run is called', () {
        List<BenchmarkBase> benchmarks = [];
        benchmarks.add(new DeltaBlue());
        benchmarks.add(new FluidMotion());
        benchmarks.add(new Richards());
        benchmarks.add(new TracerBenchmark());
        benchmarks.add(new Havlak());


        for (int i = 0; i < benchmarks.length; i ++) {
          extractScores(benchmarks[i]);
        }

        print("[benchmark] Flutter - completed.");
      });
    });
  }

  void extractScores(BenchmarkBase benchmark) async {
    List<double> scores = [];
    List<double> elapsedUs = [];

    for (int i = 0; i < 10; i++) {
      double ret = benchmark.measure();
      elapsedUs.add(ret);
      scores.add(1000000 / ret);
    }

    double mean = computeMean(elapsedUs);

    print('[benchmark] Flutter - ${benchmark.name}:(runs/sec)\t${format(scores, "\t")}\tavg us:\t$mean');
  }

  // format
  String format(List<double> scores, String metric) {
    double mean = computeMean(scores);
    double best = computeBest(scores);
//    String score = strings.padLeft(best.toStringAsFixed(2), 8, ' ');
    String score = best.toStringAsFixed(2).padLeft(8, ' ');
    if (scores.length == 1) {
      return "$score $metric";
    } else {
      final int n = scores.length;
      double standardDeviation = computeStandardDeviation(scores, mean);
      double standardError = standardDeviation / math.sqrt(n);
      double percent = (computeTDistribution(n) * standardError / mean) * 100;
      String error = percent.toStringAsFixed(1);
      return "$score $metric (${mean.toStringAsFixed(2)}±$error%)";
    }
  }

  double computeBest(List<double> scores) {
    double best = scores[0];
    for (int i = 1; i < scores.length; i++) {
      best = math.max(best, scores[i]);
    }
    return best;
  }

  double computeMean(List<double> scores) {
    double sum = 0.0;
    for (int i = 0; i < scores.length; i++) {
      sum += scores[i];
    }
    return sum / scores.length;
  }

  double computeStandardDeviation(List<double> scores, double mean) {
    double deltaSquaredSum = 0.0;
    for (int i = 0; i < scores.length; i++) {
      double delta = scores[i] - mean;
      deltaSquaredSum += delta * delta;
    }
    double variance = deltaSquaredSum / (scores.length - 1);
    return math.sqrt(variance);
  }

  double computeTDistribution(int n) {
    const List<double> TABLE = const [
      double.nan, double.nan, 12.71,
      4.30, 3.18, 2.78, 2.57, 2.45, 2.36, 2.31, 2.26, 2.23, 2.20, 2.18, 2.16,
      2.14, 2.13, 2.12, 2.11, 2.10, 2.09, 2.09, 2.08, 2.07, 2.07, 2.06, 2.06,
      2.06, 2.05, 2.05, 2.05, 2.04, 2.04, 2.04, 2.03, 2.03, 2.03, 2.03, 2.03,
      2.02, 2.02, 2.02, 2.02, 2.02, 2.02, 2.02, 2.01, 2.01, 2.01, 2.01, 2.01,
      2.01, 2.01, 2.01, 2.01, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00,
      2.00, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00, 1.99, 1.99, 1.99, 1.99, 1.99,
      1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99,
      1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99, 1.99 ];
    if (n >= 474) return 1.96;
    else if (n >= 160) return 1.97;
    else if (n >= TABLE.length) return 1.98;
    else return TABLE[n];
  }
}
