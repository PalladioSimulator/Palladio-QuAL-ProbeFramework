package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.edp2.ui.dialogs.datasource.ConfigureDatasourceDialog;

public class EDP2Tab extends AbstractLaunchConfigurationTab {

    private Text dataField;

    protected String selectedRepositoryID;
    
    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout());
        setControl(container);

        final ModifyListener modifyListener = new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                EDP2Tab.this.setDirty(true);
                EDP2Tab.this.updateLaunchConfigurationDialog();
            }
        };

        final Group dataSetGroup = new Group(container, SWT.NONE);
        dataSetGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
                false));
        final GridLayout gridLayout_2 = new GridLayout();
        gridLayout_2.numColumns = 3;
        dataSetGroup.setLayout(gridLayout_2);
        dataSetGroup.setText("Data Set");

        final Label dataSourceLabel = new Label(dataSetGroup, SWT.NONE);
        dataSourceLabel.setText("Data source:");

        dataField = new Text(dataSetGroup, SWT.BORDER | SWT.READ_ONLY);
        dataField
                .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        dataField.addModifyListener(modifyListener);

        final Button browseButton = new Button(dataSetGroup, SWT.NONE);
        browseButton.setText("Browse...");
        browseButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse
             * .swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                ConfigureDatasourceDialog dialog = new ConfigureDatasourceDialog(
                        e.display.getActiveShell(), "Select Datasource...",
                        true);
                if (dialog.open() == Dialog.OK) {
                    Repository repository = (Repository) dialog.getResult();
                    selectedRepositoryID = repository.getUuid();
                    dataField.setText(repository.toString());
                }
            }
        });

    }
    
	@Override
    public String getName() {
        return "EDP2";
    }

    @Override
    public void initializeFrom(ILaunchConfiguration configuration) {
        try {
            selectedRepositoryID = configuration.getAttribute(
                    EDP2Config.REPOSITORY_ID, "");
            Repository repository = RepositoryManager.getRepositoryFromUUID(selectedRepositoryID);
            if(repository == null)
                dataField.setText("");
            else {
                dataField.setText(repository.toString());
            }
        } catch (CoreException e) {
            selectedRepositoryID = "";
            dataField.setText("");
        }
    }

    @Override
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(EDP2Config.REPOSITORY_ID,
                selectedRepositoryID);

    }

    @Override
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(EDP2Config.REPOSITORY_ID, -1);
    }

    @Override
    public boolean isValid(ILaunchConfiguration launchConfig) {
        Repository repository = RepositoryManager.getRepositoryFromUUID(selectedRepositoryID);
        if (repository == null) {
            setErrorMessage("Data source is missing!");
            return false;
        }
        return true;
    }
    
    @Override
    public void activated(ILaunchConfigurationWorkingCopy workingCopy) {}

    @Override
    public void deactivated(ILaunchConfigurationWorkingCopy workingCopy) {}

}
