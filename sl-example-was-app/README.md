# WAS application example
Simple servlet example for running on WAS

## Adding application to WAS
Upload war (asset):
 * Select Applications->Application Types->Assets
 * Press Import and select the relevant war from the local file system
 * Press next several times (according to the wizard) and 'Save' at the end
 
Create the business level application:
 * Select Applications->New Application->New Business Level Application
 * Fill application name (for example MyApp) and press Apply
 * Add assert (from item 1) to the application
 * Press next several times (according to the wizard) and 'Save' at the end
  
## Updating war of exiting application
Update asset:
 * Select Applications->Application Types->Assets
 * Select asset to be updated and press 'Update'
 * Press next several times (according to the wizard) and 'Save' at the end

Reassign update asset to the application:
 * Select Applications->Application Types->Business-level applications
 * Stop the application: check the application check-box and press 'Stop'
 * Press on the application entry
 * Delete the asset from Deployed assets
 * Add the asset again -  press next several times (according to the wizard) and 'Save' at the end

 