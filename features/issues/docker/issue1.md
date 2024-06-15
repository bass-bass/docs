# failed to execute script docker-compose
`docker-compose`コマンド実行時にエラー

# error
```
Traceback (most recent call last):
  File "docker-compose", line 3, in <module>
  File "compose/cli/main.py", line 81, in main
  File "compose/cli/main.py", line 200, in perform_command
  File "compose/cli/command.py", line 60, in project_from_options
  File "compose/cli/command.py", line 152, in get_project
  File "compose/cli/docker_client.py", line 41, in get_client
  File "compose/cli/docker_client.py", line 170, in docker_client
  File "docker/api/client.py", line 197, in __init__
  File "docker/api/client.py", line 221, in _retrieve_server_version
docker.errors.DockerException: Error while fetching server API version: ('Connection aborted.', FileNotFoundError(2, 'No such file or directory'))
[2880] Failed to execute script docker-compose
```

# solution
docker desktopが起動していないため、起動する
