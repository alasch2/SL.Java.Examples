SETLOCAL

:: SET SL_TOKEN_FILE_PATH=C:\dev\SeaLights\sl-agents-token.txt
SET SL_TOKEN_FILE_PATH=path/to/the/file/containint/jwt_token

SET SL_BUILD_SESSION_ID_PATH=buildSession4Tests.txt

SET SL_TEST_STAGE=Integration Tests

rspec spec\calc_service_spec.rb