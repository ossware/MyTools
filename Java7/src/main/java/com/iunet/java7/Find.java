package com.iunet.java7;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Find {
	public static class Finder extends SimpleFileVisitor<Path> {

		private final PathMatcher matcher;
		private int numMatches = 0;
		private int totalFiles = 0;

		Finder(String pattern) {
			matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
		}

		// Compares the glob pattern against
		// the file or directory name.
		void find(Path file) {
			Path name = file.getFileName();
			if (name != null && matcher.matches(name)) {
				numMatches++;
				System.out.println(file);
			}
			totalFiles++;
		}

		// Prints the total number of
		// matches to standard out.
		void done() {
			System.out.println("Matched: " + numMatches);
			System.out.println("totalFiles: " + totalFiles);
		}

		// Invoke the pattern matching
		// method on each file.
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			find(file);
			return FileVisitResult.CONTINUE;
		}

		// Invoke the pattern matching
		// method on each directory.
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
			find(dir);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			System.err.println(exc);
			return FileVisitResult.CONTINUE;
		}
	}


	public static void main(String[] args) throws IOException {
		Path startingDir = Paths.get("d:/Program Files");
		String pattern = "*";

		long start = System.currentTimeMillis();
		
		Finder finder = new Finder(pattern);
		Files.walkFileTree(startingDir, finder);
		finder.done();
		
		long end = System.currentTimeMillis();
		System.out.println("查询所有文件总耗时：" + (end - start) + "ms");
		
		System.gc();
	}
}
