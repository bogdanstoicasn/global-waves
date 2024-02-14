#### Copyright Stoica Mihai-Bogdan 325CA (bogdanstoicasn@yahoo.com)


# Project Global Waves - Stage 2

## Overview

The GlobalWaves Project - Stage 2 - Pagination introduces significant
enhancements to the simulated music streaming application developed
in the previous stage. This iteration focuses on creating a more user-friendly
and organized interface by implementing a pagination system. Users can now
navigate through different pages, each serving a specific purpose, providing
a more structured exploration of the platform's content.
I used the codebase provided by the POO team.

## Table of Contents

- [Overview](#Overview)
- [Table of Contents](#Table-of-Contents)
- [Previous stage](#Previous-stage)
- [Requirements](#Requirements)
- [Package Structure](#Package-Structure)
- [Implementation Details](#Implementation-Details)
- [Design Patterns](#Design-Patterns)
- [Resources](#Resources)

## Previous stage

Implemented song, playlist and podcasts manipulation commands.

## Requirements

The homework must be implemented in Java. The input files will be in JSON
format. We use SDK 17, and the project is built using Gradle.


## Package Structure

### 1. app

- `Admin.java`: Handles administrative functionalities.
- `CommandRunner.java`: Manages the execution of commands within the application.

### 2. audio

#### Collections

- `Album.java`: Represents an album with a collection of songs.
- `AlbumSingleton.java`: Ensures a single instance of each album.
- `AudioCollection.java`: Abstract class representing a generic audio collection.
- `Playlist.java`: Represents a playlist containing multiple songs.
- `PlaylistOutput.java`: Handles the output logic for playlists for JSON.
- `Podcast.java`: Represents a podcast with multiple episodes.
- `ShowAlbums.java`: Displays the list of available albums.

#### Files

- `AudioFile.java`: Abstract class for different types of audio files.
- `Episode.java`: Represents an episode in a podcast.
- `Song.java`: Represents an individual song.

- `LibraryEntry.java`: Represents an entry in the audio library, abstract class.

### 3. player

- `Player.java`: Manages audio playback.
- `PlayerSource.java`: Defines the source of audio for the player.
- `PlayerStats.java`: Keeps track of player statistics.
- `PodcastBookmark.java`: Manages bookmarks for podcast episodes.

### 4. searchBar

- `Filters.java`: Implements filters for searching.
- `FilterUtils.java`: Utility methods for filtering.
- `SearchBar.java`: Represents the search bar.

### 5. user

- `Announcement.java`: Represents announcements to users.
- `Artist.java`: Represents an artist.
- `Event.java`: Represents an event.
- `Host.java`: Represents a host for podcasts.
- `Merch.java`: Represents merchandise.
- `User.java`: Represents a user of the application.

### 6. utils

- `Enums.java`: Contains various enums used across the application.



## Implementation Details

### Admin related operations

> **addUser** - adds a/an new user/host/artists to the platform

> **deleteUser** - deletes a/an user/host/artist from the platform

> **showAlbums** - displays all the albums of an artist

> **showPodcasts** - displays all the podcasts of a host

### User related operations

> **addAlbum** - adds a new album to the platform

> **removeAlbum** - removes an album from the platform

> **addEvent** - adds a new event to the platform

> **removeEvent** - removes an event from the platform

> **addMerch** - adds a new merchandise to the platform

> **removeMerch** - removes a merchandise from the platform

### Host related operations

> **addPodcast** - adds a new podcast to the platform

> **removePodcast** - removes a podcast from the platform

> **addAnnouncement** - adds a new announcement to the platform

> **removeAnnouncement** - removes an announcement from the platform

### User related operations

> **switchConnectionStatus** - switches the connection status of a user

### General operations

> **getTop5Albums** - displays the top 5 albums of the platform

> **getTop5Artists** - displays the top 5 artists of the platform

> **getAllUsers** - displays all the users of the platform

> **getAllOnlineUsers** - displays all the online users of the platform

### File system operations

> **printCurrentPage** - displays the current page of a user

> **changePage** - changes the current page of a user

I choose to implement an interface for reading the commands, which is
implemented in the `CommandFactory.java` file. This way, I can easily
add new commands to the application, without having to modify the
`Main.java` file. The `CommandFactory.java` file also contains the
`executeCommand` method, which is used to execute the commands.

I experienced some difficulties while implementing the functionality
for deleting users. I had to make sure that all the references to the
deleted user are removed, and that the user is removed from all the
collections that contain it. When deleting a user, all of it's content
must be deleted as well. To do that I had to make sure that no other user
has content(album, playlist, podcast, page) that contains the deleted user.

The adding commands where simple. I just had to make sure that the new instance
of what I was adding wasn't already in the platform. 

I added the singleton class for albums and commands. The class ads/removes the
albums. The commands field is used just to keep track of the commands, for
error handling in future stages.

## Design Patterns

### Singleton

The Singleton design pattern is a creation pattern that ensures a class has
only one instance and provides a global point of access to that instance.
This pattern is particularly useful when exactly one object is needed to
coordinate actions across the system.

In my implementation, I used the Singleton pattern for the `AlbumSingleton`
class. This class ensures that only one instance of each album is created.

### Factory

The Factory design pattern is a creation pattern that defines an interface
for creating an object, but lets subclasses decide which class to instantiate.
This pattern is particularly useful when the creation of an object is complex
and requires a lot of boilerplate code.

In my implementation, I used the Factory pattern for the `CommandFactory`
class. This class defines an interface for creating commands, but lets
subclasses decide which command to instantiate. `CommandFactory` is not
in total compliance with the Factory pattern, as it does not have any
concrete implementations for creating commands.

### Resources

- [Singleton](https://refactoring.guru/design-patterns/singleton)
- [Factory](https://refactoring.guru/design-patterns/factory-method)

