/**
 * This file is part of the Sandy Andryanto Company Profile Website.
 *
 * @author     Sandy Andryanto <sandy.andryanto404@gmail.com>
 * @copyright  2024
 *
 * For the full copyright and license information,
 * please view the LICENSE.md file that was distributed
 * with this source code.
 */

package com.api.backend.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.api.backend.config.FileStorageProperties;
import com.api.backend.exception.FileStorageException;
import com.api.backend.exception.MyFileNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String readFileFromResources(String filename) throws IOException {

		File initialFile = new File(filename);
		InputStream inputStream = new FileInputStream(initialFile);
		// Creating a BufferedReader to read text from the InputStream efficiently
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		// StringBuilder to accumulate the lines read from the file
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		// Reading each line from the file and appending it to the StringBuilder
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		// Closing the BufferedReader
		reader.close();
		// Returning the contents of the file as a string
		return stringBuilder.toString();
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		UUID uuid = UUID.randomUUID();
		String filenameReal = file.getOriginalFilename();
		String fileExtension = filenameReal.substring(filenameReal.lastIndexOf(".") + 1);
		String fileName = uuid + "." + fileExtension;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}