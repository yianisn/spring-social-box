# spring-social-box [![Build Status](https://travis-ci.org/yianisn/spring-social-box.svg?branch=develop)](https://travis-ci.org/yianisn/spring-social-box)

spring social adapter for [box](https://box.com)

## features

### Folder Operations

* [create a new folder](https://developers.box.com/docs/#folders-create-a-new-folder)
* [update information about a folder](https://developers.box.com/docs/#folders-update-information-about-a-folder)
  Supported attributes: name, description, tags
* [delete a folder](https://developers.box.com/docs/#folders-delete-a-folder)

### File Operations

* [upload a new file](https://developers.box.com/docs/#files-upload-a-file)
* [update information about a file](https://developers.box.com/docs/#files-update-a-files-information)
  Supported attributes: name, description, tags
* [delete a file](https://developers.box.com/docs/#files-delete-a-file)

### Comments Operations

unsupported

### Collaborations Operations

unsupported

### Events Operations

unsupported

### Shared Items Operations

unsupported

### Search Operations

unsupported

### Users Operations

* retrieve logged in user's profile using spring social's generic API
* retrieve [current user's information](https://developers.box.com/docs/#users-get-the-current-users-information)

### Groups Operations

unsupported

### Tasks Operations

unsupported

## exception handling

* OAuth2 operations will throw SpringSocial exceptions
* Box API operations will throw SpringSocial exceptions

## License

spring-social-box is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0.html)